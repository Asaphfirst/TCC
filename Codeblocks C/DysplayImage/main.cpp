#include <opencv2/opencv.hpp>
#include "opencv2/core.hpp"
#include "opencv2/imgproc.hpp"
#include "opencv2/highgui.hpp"
#include <stdio.h>
#include "opencv2/imgproc/imgproc.hpp"

using namespace cv;
using namespace std;



int main(int argc, char** argv)
{
    double minArea = 400.0;  // default Double
	double minPerimeter = 0.0;  // default Double
	double minWidth = 0.0;  // default Double
	double maxWidth = 100.0;  // default Double
	double minHeight = 0.0;  // default Double
	double maxHeight = 1000.0;  // default Double
	double solidity[] = {0, 90.61433447098976};
	double maxVertices = 1000000.0;  // default Double
	double minVertices = 0.0;  // default Double
	double minRatio = 0.0;  // default Double
	double maxRatio = 1.5;  // default Double

    int cx=0,cy=0;

    if (argc!=2)
    {
        printf("Usage: limiar <input_image>\n");
        exit(-1);
    }

    Mat input,limiar;

    input = imread(argv[1], 0); // 0 = Open image in grayscale
    if (!input.data)
    {
        printf("No image data. Check the file %s\n", argv[1]);
        exit(-1);
    }
    namedWindow("Input Image", WINDOW_AUTOSIZE);
    imshow("Input Image", input);
    waitKey(0);

    threshold(input,limiar,218,255,CV_THRESH_BINARY);
    namedWindow("Limiar Image", WINDOW_AUTOSIZE);
    imshow("Limiar Image", limiar);
    waitKey(0);


    /* Find contours */

    vector<vector<Point> > contours;

    findContours(limiar,contours,RETR_EXTERNAL,CHAIN_APPROX_SIMPLE);
    vector<vector<Point> > hull(contours.size());
    Mat output = Mat::zeros(limiar.rows,limiar.cols,CV_8UC3);
    Mat output2 = Mat::zeros(limiar.rows,limiar.cols,CV_8UC3);

    /* check the number of contours */
    if(contours.size() == 0)
    {
        cout << "no objects detected" << endl;
    }else{
        cout << "number of objects detected: " << contours.size() << endl;
    }

    vector<Moments> mu(contours.size()); ///////

    RNG rng(0xffffffff); // random number generated
    for(int i=0;i<contours.size();i++) // Draw contours
    {
      Rect bb = boundingRect(Mat(contours[i]));

			if (bb.width > minWidth && bb.width < maxWidth)
            {
                //cout << "the bb.width is: " << bb.width << endl;
                if (bb.height > minHeight && bb.height < maxHeight)
                {
                    //cout << "the bb.height is: " << bb.height << endl;
                    double area = contourArea(contours[i]);
                    if (area > minArea)
                    {
                        //cout << "the area is: " << area << endl;
                        convexHull(Mat(contours[i], true), hull[i]);
                        double solid = 100 * area / contourArea(hull[i]);

                        if (solid > solidity[0] && solid < solidity[1])
                        {
                            //cout << "the solidity is: " << solid << endl;
                            double ratio = (double) bb.width / (double) bb.height;
                            if (ratio > minRatio && ratio < maxRatio)
                            {
                                //cout << "the ratio is: " << ratio << endl;
                                Scalar color = Scalar( rng.uniform(0, 255), rng.uniform(0,255), rng.uniform(0,255) ); // get a different color for each contour
                                drawContours(output,contours,i, color);  // draw desired contour
                                rectangle(output2,bb,color);


                                mu[i] = moments(Mat(contours[i]));
                                cx = int(mu[i].m10/mu[i].m00);
                                cy = int(mu[i].m01/mu[i].m00);
                            }
                        }
                    }
                }
            }
    }

    imshow("result",output);
    waitKey();

    imshow("result",output2);
    waitKey();

    printf("posicao em x: %d posicao em y: %d", cx,cy);
    return 0;
}

