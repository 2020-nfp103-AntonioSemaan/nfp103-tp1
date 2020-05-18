package Exercice7;

public class Matrice {
    private int[][] matrix;

    public Matrice(int[][] m) {
        matrix = m;
    }

    public Matrice multiply(Matrice m) {
        
        int resultRowCount, resultColCount;
        resultRowCount = this.matrix.length;
        resultColCount = m.getActualValues()[0].length;
        int[][] result = new int[resultRowCount][resultColCount];
        MultiplyThread[] mts = new MultiplyThread[resultRowCount * resultColCount];
        int row = 0, col = 0;
        //System.out.println("length of thread array "+mts.length);
        for (int i = 0; i < mts.length; i++) {
            /*System.out.printf("populating [%d][%d]\n",
            row,col);/**/
            mts[i] = new MultiplyThread(result, this.matrix, m.getActualValues(), row, col++);
            if (col == resultColCount) {
                row++;
                col = 0;
            }
            mts[i].start();/**/
        }
        try {
            for (MultiplyThread mt : mts) {
                mt.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }/**/
        return new Matrice(result);
    }

    public int[][] getActualValues(){
        return matrix;
    }

    public String toString(){
        String s="";
        for(int i=0;i<this.matrix.length;i++){
            s+="|";
            for(int j=0;j<this.matrix[0].length;j++){
                s+=this.matrix[i][j];
                if(j<this.matrix[0].length-1)
                    s+=", ";
            }
            s+="|\n";
        }
        return s;
    }

    public static void main(String[] args) {
        Matrice m1 = new Matrice(
            new int[][]{
                new int[]{1,4,6,2,8},
                new int[]{13,1,5,0,10},
                new int[]{4,9,7,1,12}
            }
        );
        Matrice m2 = new Matrice(
            new int[][]{
                new int[]{13,12,10},
                new int[]{0,4,8},
                new int[]{10,9,2},
                new int[]{1,17,5},
                new int[]{5,1,3}
            }
        );
        Matrice resultat=m1.multiply(m2);
        System.out.println(resultat.toString());
    }

    private class MultiplyThread extends Thread{
        int[][] result, firstMatrix, secondMatrix;
        int rowIndex, colIndex;
        public MultiplyThread(int[][] result,int[][] firstM,
            int[][] secondM,int rowIndex, int colIndex){
                this.result=result;
                this.firstMatrix=firstM;
                this.secondMatrix=secondM;
                this.rowIndex=rowIndex;
                this.colIndex=colIndex;
        }
        
        public void run(){
            int total=0,counter=0;
            
            while(counter<this.firstMatrix[0].length){
                /*System.out.printf("[%d][%d] 1- row %d col %d val %d\n2- row %d col %d val %d\n",
                    rowIndex,colIndex,
                    rowIndex,counter,firstMatrix[rowIndex][counter],
                    counter,colIndex,secondMatrix[counter][colIndex]);
                */
                total+=firstMatrix[rowIndex]
                        [counter]
                    *secondMatrix[counter]
                        [colIndex];
                counter++;
            }
            result[rowIndex][colIndex]=total;
        }
    }
}