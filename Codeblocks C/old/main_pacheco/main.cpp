#include <opencv2/opencv.hpp>

using namespace cv;

int main()
{
  Mat input;
  input = imread("pic13.jpg", 1);
  if (!input.data) {
    printf("No image data. Check the file %s\n", input);
    exit(-1);
  }

  namedWindow("Input Image", WINDOW_AUTOSIZE);
  imshow("Input Image", input);
  waitKey(0);
  return 0;
}
