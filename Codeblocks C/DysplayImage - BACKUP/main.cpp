#include <opencv2/opencv.hpp>
#include "opencv2/core.hpp"
#include "opencv2/imgproc.hpp"
#include "opencv2/highgui.hpp"
#include <stdio.h>

using namespace cv;
using namespace std;

int main(int argc, char** argv)
{
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
    Mat output = Mat::zeros(limiar.rows,limiar.cols,CV_8UC3);

    /* check the number of contours */
    if(contours.size() == 0)
    {
        cout << "no objects detected" << endl;
    }else{
        cout << "number of objects detected: " << contours.size() << endl;
    }
    RNG rng(0xffffffff); // random number generated
    for(int i=0;i<contours.size();i++) // Draw contours
    {
        Scalar color = Scalar( rng.uniform(0, 255), rng.uniform(0,255), rng.uniform(0,255) ); // get a different color for each contour
        drawContours(output,contours,i, color);  // draw actual contour
    }

    imshow("result",output);
    waitKey();

    // contour's area

    //double area = contourArea()

    return 0;
}

//    // / Find contours 1/2 working
//    vector<vector<Point> > contours;
//    vector<Vec4i> hierarchy;
//    RNG rng(12345);
//    findContours( limiar, contours, hierarchy, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE, Point(0, 0) );
//    // / Draw contours
//    Mat drawing = Mat::zeros( limiar.size(), CV_8UC3 );
//    for( int i = 0; i< contours.size(); i++ )
//    {
//        Scalar color = Scalar( rng.uniform(0, 255), rng.uniform(0,255), rng.uniform(0,255) );
//        drawContours( drawing, contours, i, color, 2, 8, hierarchy, 0, Point() );
//    }
//    namedWindow("Result window", WINDOW_AUTOSIZE);
//    imshow( "Result window", drawing );
//    waitKey(0);

// Nao sei mais oque eh isso abaixo
//    vector<vector<Point> > contours0;
//
//    findContours(limiar,contours0,RETR_EXTERNAL,CHAIN_APPROX_SIMPLE);//hierarchy,
//    drawContours(limiar,contours0,FILLED,8, 3);
//    //drawContours(limiar,contours0,)
//    namedWindow("contourn", WINDOW_AUTOSIZE);
//    imshow("contourn", limiar);
//    waitKey(0);
