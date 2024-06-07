import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {

	public static void main(String[] args) {
		try {
			// Load the OpenCV library
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			
			// Read the input image
	        String inputImagePath1 = "D:\\Penguin.jpg";
	        Mat inputImage1 = Imgcodecs.imread(inputImagePath1); // Read the image using Imgcodecs
	        
	        String inputImagePath2 = "D:\\photo2.jpg";
	        Mat inputImage2 = Imgcodecs.imread(inputImagePath2); // Read the image using Imgcodecs
	        
	        String inputImagePath3 = "D:\\photo3.jpg";
	        Mat inputImage3 = Imgcodecs.imread(inputImagePath3); // Read the image using Imgcodecs
	        
	        
	        // Check if the image was loaded successfully
	        if (inputImage1.empty()) {
	            System.out.println("Failed to load the input image");
	            return;
	        }
	        
	        Mat image1 = new Mat();
	     // display the Original images
	        HighGui.imshow("Original Image1", inputImage1);
	        HighGui.imshow("Original Image2", inputImage2);
	        HighGui.imshow("Original Image3", inputImage3);
	        
	        
	        convert2Gray(inputImage1, image1, 1); //call convert to gray level method
	        
	       
	        robertsOperator(image1, 1);
	        sobelOperator(image1, 1);
	        prewittOperator(image1, 1);
	        
	        robertsOperator(inputImage2, 2);
	        sobelOperator(inputImage2, 2);
	        prewittOperator(inputImage2, 2);
	        
	        robertsOperator(inputImage3, 3);
	        sobelOperator(inputImage3, 3);
	        prewittOperator(inputImage3, 3);
	        HighGui.waitKey();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** method to convert original image from RGB level to gray level */
	private static void convert2Gray(Mat originalImage, Mat grayImage, int i) {
        Imgproc.cvtColor(originalImage, grayImage, Imgproc.COLOR_BGR2GRAY); // convert the image color level
        HighGui.imshow("Grayscale Image"+i, grayImage); // display the image on the screen
        //HighGui.waitKey(); // Wait to display the image
	}
	
	private static void robertsOperator(Mat image, int m) {
		 int cols = image.cols(); // Get the width of the gray image
	     int rows = image.rows(); // Get the height of the gray image
	        
		int[][] Gx = {{1, 0}, {0, -1}};
        int[][] Gy = {{0, 1}, {-1, 0}};
        Mat result = Mat.zeros(rows, cols, CvType.CV_8UC1);
        
        for (int i = 0; i < rows-1; i++) {
            for (int j = 0; j < cols-1; j++) {
                int gx = 0;
                int gy = 0;
                for (int x = 0; x < Gx.length; x++) {
                    for (int y = 0; y < Gx[0].length; y++) {
                    	int intensity = (int) image.get( i+x - 1, j+y -1)[0];
                        gx += intensity * Gx[x][y];
                        gy += intensity * Gy[x][y];
                    }
                }
                int magnitude = (int) Math.sqrt(Math.pow(gx, 2) + Math.pow(gy, 2));
                result.put(i, j, Math.min(255, magnitude));
            }
        }
        HighGui.imshow("Reberts Image"+m, result);
	}
	
	
	private static void sobelOperator(Mat image, int m) {
		 int cols = image.cols(); // Get the width of the gray image
	     int rows = image.rows(); // Get the height of the gray image
	        
		int[][] Gx = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        int[][] Gy = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
        Mat result = Mat.zeros(rows, cols, CvType.CV_8UC1);
        
        for (int i = 0; i < rows-1; i++) {
            for (int j = 0; j < cols-1; j++) {
                int gx = 0;
                int gy = 0;
                for (int x = 0; x < Gx.length; x++) {
                    for (int y = 0; y < Gx[0].length; y++) {
                    	int intensity = (int) image.get( i+x - 1, j+y -1)[0];
                        gx += intensity * Gx[x][y];
                        gy += intensity * Gy[x][y];
                    }
                }
                int magnitude = (int) Math.sqrt(Math.pow(gx, 2) + Math.pow(gy, 2));
                result.put(i, j, Math.min(255, magnitude));
            }
        }
        HighGui.imshow("Sobel Image"+m, result);
	}
	
	
	private static void prewittOperator(Mat image, int m) {
		int cols = image.cols(); // Get the width of the gray image
	    int rows = image.rows(); // Get the height of the gray image
	        
		int[][] Gx = {{-1, 0, 1}, {-1, 0, 1}, {-1, 0, 1}};
        int[][] Gy = {{-1, -1, -1}, {0, 0, 0}, {1, 1, 1}};
        Mat result3 = Mat.zeros(rows, cols, CvType.CV_8UC1);
        
        for (int i = 0; i < rows-1; i++) {
            for (int j = 0; j < cols-1; j++) {
                int gx = 0;
                int gy = 0;
                for (int x = 0; x < Gx.length; x++) {
                    for (int y = 0; y < Gx[0].length; y++) {
                    	int intensity = (int) image.get( i+x - 1, j+y -1)[0];
                        gx += intensity * Gx[x][y];
                        gy += intensity * Gy[x][y];
                    }
                }
                int magnitude = (int) Math.sqrt(Math.pow(gx, 2) + Math.pow(gy, 2));
                result3.put(i, j, Math.min(255, magnitude));
            }
        }
        HighGui.imshow("Prewitt Image"+m, result3);
	}
}
