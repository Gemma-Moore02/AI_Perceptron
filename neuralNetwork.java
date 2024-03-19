// Moore, Gemma
// AI perceptron boolean AND, OR

// import for file reading
import java.io.*;
import java.util.Scanner;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Random;

public class neuralNetwork{

    // random double between -0.5 and 0.5
    public static double randomInRange(double start, double end) {
        Random r = new Random();
        return r.nextDouble() * (end - start) + start;
    }

    // activation/step function
    public static int step(double x1, double x2, double w1, double w2, double theta){
        double sum = ((x1*w1)+(x2*w2))-theta;
        if(sum < 0)
            return 0;
        else
            return 1;
    }

    //update weight 
    public static double updateWeight(double w, double alpha, double x, double e){
        return w + (alpha * x * e);

    }

    // check for error 
    public static boolean checkError(double[] e){
        for(int i = 0; i < e.length; i++){
            if (e[i] > 0 || e[i] < 0)
                return false;
        }

        return true;
    }


    public static void main (String[] args) throws FileNotFoundException{   

        // initialize random variables 
        double w1 = randomInRange(-0.5, 0.5);
        double w2 = randomInRange(-0.5, 0.5);

        // initialize fixed variables
        double alpha = 0.1;
        double theta = 2.3664064;
        double [] e = {1, 1, 1, 1};
        int epoch = 1;    
        int [] Yd = new int[4];
        int [] x1 = new int[4];
        int [] x2 = new int[4];
        int [] Y = new int[4];       
        String line = "";
        boolean errorFlag = true;
        String weight1 = "";
        String weight2 = "";
        String oldWeight1 = "";
        String oldWeight2 = "";
        
        // number formatter
        NumberFormat nf = DecimalFormat.getInstance();
        nf.setMaximumFractionDigits(1);  

        // training data
        File trainingData = new File("trainingDataAND.txt");
        // scanner for traning data 
        Scanner scan = new Scanner(trainingData);    

        // implement training data using parallel arrays 
        // read in x1 as array 
        for(int i = 0; i < x1.length ; i++){
            x1[i] = scan.nextInt();
        }

        // read in x2 as array 
        for(int i = 0; i < x1.length; i++){
            x2[i] = scan.nextInt();
        }

        // read in Yd (desired output) as array 
        for(int i = 0; i < x1.length; i++){
            Yd[i] = scan.nextInt();
        }

        // while there is still an error and the epoch is less than 100
        while(!checkError(e) && epoch <= 100){

            // display line and headers
            System.out.println("Epoch  x1\tx2\tYd\tw1\tw2\tY\te\tw1\tw2");
            System.out.println("____________________________________________________________________________");        
            System.out.print(epoch + "  ");
            
            for(int i = 0; i < Yd.length; i++){
                // calculate actual value 
                Y[i] = step(x1[i], x2[i], w1, w2, theta);
                // calculate the error 
                e[i] = Yd[i] - Y[i];
                // set old weights for output 
                oldWeight1 = nf.format(w1);
                oldWeight2 = nf.format(w2);
                // update the weights
                w1=updateWeight(w1, alpha, x1[i], e[i]);
                w2=updateWeight(w2, alpha, x2[i], e[i]);
                // string formatted weight
                weight1 = nf.format(w1);
                weight2 = nf.format(w2);
                // print the results 
                System.out.println("\t" + x1[i] + "\t" + x2[i] + "\t" + Yd[i] + "\t" + oldWeight1 
                    + "\t" + oldWeight2 + "\t" + Y[i] + "\t" + e[i] + "\t" + weight1 + "\t" + weight2);                

            }

            // print bottom line and icrement epoch
            System.out.println("____________________________________________________________________________");        
            epoch++; 

        }
    
    }
    
    
}