#include <opencv/cv.h>
#include "opencv/highgui.h"
#include "opencv/cxcore.h"


int main()
{
    IplImage *INPUT,*CLONE;             // Importa e abre a imagem

    //INPUT = cvLoadImage("Lena.png",1);
    INPUT = cvLoadImage("pic12.jpg",1);
    //cvNamedWindow("Imagem Original",1);
    cvShowImage("Imagem Original",INPUT);
    cvWaitKey(0);
    //cvDestroyWindow("Imagem Original");


    IplImage *GRAY = cvCreateImage(CvSize(500,500),8,1);   // cria uma imagem do mesmo tamanho da original com 1 canal // 512 para Lena
    //cvNamedWindow("Imagem criada",1);
    cvShowImage("Imagem criada",GRAY);
    cvWaitKey(0);
    //cvDestroyWindow("Imagem criada");
    cvCvtColor(INPUT,GRAY,CV_RGB2GRAY);                    // RBG to Gray

    cvShowImage("Escala de cinza",GRAY);                   // Mostra a imagem com escada de cinza
    cvWaitKey(0);
    //cvDestroyWindow("Escala de cinza");

    IplImage *LIMIAR = cvCreateImage(CvSize(500,500),8,1); // Cria imagem para receber a limiar
    cvThreshold(GRAY,LIMIAR,218,255,CV_THRESH_BINARY);

    cvShowImage("Apos Limiar",LIMIAR);                   // Mostra a imagem com escada de cinza
    cvWaitKey(0);
    //cvDestroyWindow("Apos Limiar");



    //cvDestroyWindow("Output");
    cvReleaseImage(&GRAY);
}


//    CLONE = cvCloneImage(INPUT);          // Clona a imagem para ficar com o mesmo tamanho
