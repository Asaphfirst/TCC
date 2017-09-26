#include "opencv/cv.h"
#include "opencv/highgui.h"
//#include

int main()
{
    IplImage *INPUT;

    INPUT = cvLoadImage("Lena.png",1);

    cvNamedWindow("Output",1);

    cvShowImage("Output",INPUT);
    cvWaitKey(0);
    cvDestroyWindow("Output");
    cvReleaseImage(&INPUT);
}
