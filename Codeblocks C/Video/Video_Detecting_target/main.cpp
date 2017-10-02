#include <opencv2/opencv.hpp>
#include "opencv2/core.hpp"
#include "opencv2/imgproc.hpp"
#include "opencv2/highgui.hpp"
#include <stdio.h>
#include "opencv2/imgproc/imgproc.hpp"
#include <iostream>
#include <sstream>

using namespace cv;
using namespace std;

double minArea = 300.0;  // default Double
double minPerimeter = 0.0;  // default Double
double minWidth = 0.0;  // default Double
double maxWidth = 200.0;  // default Double
double minHeight = 0.0;  // default Double
double maxHeight = 1000.0;  // default Double
double solidity[] = {0,60};//{0, 90.61433447098976}; 60 works
double maxVertices = 1000000.0;  // default Double
double minVertices = 0.0;  // default Double
double minRatio = 0.0;  // default Double
double maxRatio = 1.5;  // default Double
int cx=0,cy=0;
Mat input,limiar,gray,dilated;

Mat dilatekernel;
Point anchor = Point(-1,-1);

int low_r=0, low_g=177, low_b=0;
int high_r=118, high_g=255, high_b=159;

void find_target(vector<vector<Point> > &contours, vector<vector<Point> > &hull,Mat &output,Mat &output2,double minArea, double minWidth, double maxWidth, double minHeight, double maxHeight, double solidity[], double minRatio, double maxRatio);

int main(int argc, char** argv)
{
    VideoCapture cap("Video7_512p_30fps_1led_exposure-mode_off.h264");//h264 videos 2 e 4 not good
    if (!cap.isOpened())
    {
        cout << "error opening the file" << endl;
        return -1;
    }
    while(1)
    {
        Mat frame;
        Mat original;
        cap >> frame;
        cap >> original;

        //cout << "frame channels" << frame.channels() << endl;
        //cout << "limiar channels" << limiar.channels() << endl;

        //cvtColor(frame,gray,COLOR_RGB2GRAY); em vez de tranformar para escala de cinza e depois fazer a limiar
        inRange(frame,Scalar(low_b,low_g,low_r), Scalar(high_b,high_g,high_r),limiar);
        dilate(limiar,dilated,dilatekernel,anchor,1);

        //threshold(gray,limiar,218,255,CV_THRESH_BINARY); em vez de tranformar para escala de cinza e depois fazer a limiar
        //namedWindow("Limiar Image", WINDOW_AUTOSIZE);
        //imshow("Limiar Image", limiar);

        /* Find contours */
        vector<vector<Point> > contours;
        findContours(dilated,contours,RETR_EXTERNAL,CHAIN_APPROX_SIMPLE);
        vector<vector<Point> > hull(contours.size());
        Mat output = Mat::zeros(limiar.rows,limiar.cols,CV_8UC3);
        Mat output2 = Mat::zeros(limiar.rows,limiar.cols,CV_8UC3);

        /* check the number of contours */
        if(contours.size() == 0)
        {
            cout << "no objects detected" << endl;
        }else{
           // cout << "number of objects detected: " << contours.size() << endl;
        }

        // find target and draw a filled circle
        find_target(contours,hull,output,output2,minArea,minWidth,maxWidth,minHeight,maxHeight,solidity,minRatio,maxRatio);

        //waitKey(100);
        if (output.empty())
            break;
        if(!output.empty())
        imshow("Target", output);

          if(!dilated.empty())
        imshow("Dilated", dilated);

        if(!limiar.empty())
        imshow("limiar", limiar);

        if(!original.empty())
        imshow("original", original);

        char c=(char)waitKey(25);
        if(c==27)
            break;
    }

    cap.release();

    destroyAllWindows();

    return 0;
}

void find_target(vector<vector<Point> > &contours, vector<vector<Point> > &hull,Mat &output,Mat &output2,double minArea, double minWidth, double maxWidth, double minHeight, double maxHeight, double solidity[], double minRatio, double maxRatio)
{
    vector<Moments> mu(contours.size());
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

                                circle(output,Point2f(cx,cy),5,color,FILLED,LINE_8);
                                cout << "Centro"<< Point2f(cx,cy) << endl;
                            }
                        }
                    }
                }
            }//else
            //cout << "problem on bb.width" << bb.width << endl;
    }
}
