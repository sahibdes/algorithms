import java.util.Arrays;


public class ArrayTypes {

	/**
	 * Merge function for merge sort. Merges two arrays based on the value at the same index. 
	 * @param arrL
	 * @param arrR
	 * @param arr
	 */
	public static void merge(int[] arrL,int[] arrR,int[] arr){
		
		int nL=arrL.length;
		int nR=arrR.length;
		int indexLeft=0;
		int indexRight=0;
		int finalIndex=0;
		while(indexLeft<nL && indexRight<nR){
			if(arrL[indexLeft]<=arrR[indexRight]){
				arr[finalIndex]=arrL[indexLeft];
				indexLeft++;
			}
			else{
				arr[finalIndex]=arrR[indexRight];
				indexRight++;
			}
			finalIndex++;
		}
		
		while(indexLeft<nL){
			arr[finalIndex++]=arrL[indexLeft++];
		}
		
		while(indexRight<nR){
			arr[finalIndex++]=arrR[indexRight++];
		}
	}
	
	
	/**
	 * Recursive merge sort algorithm
	 * @param arr
	 * @param left
	 * @param right
	 */
	public static void mergeSort(int[] arr,int left,int right){
		if(arr.length<2)
			return;

		int mid=(arr.length)/2;
		int[] arrLeft=new int[mid];
		int[] arrRight=new int[arr.length-mid];
		
		for(int i=0;i<mid;i++)
			arrLeft[i]=arr[i];
		
		for(int i=mid;i<arr.length;i++)
			arrRight[i-mid]=arr[i];
		
		mergeSort(arrLeft,left,mid);
		mergeSort(arrRight,mid,right);
		merge(arrLeft,arrRight,arr);
	}
	
	public static void quickSort(int[] arr,int start,int end){
		System.out.println(start);
		System.out.println(end);
		if(start<end){
		int pIndex=partition(arr,start,end);
		System.out.println(pIndex);
		quickSort(arr,start,pIndex-1);
		quickSort(arr,pIndex+1,end);
		}
	}
	
	public static int partition(int[] arr,int start,int end){
		int pIndex=start;
		int pivot=arr[end];
		for(int i=start;i<end;i++){
			if(arr[i]<=pivot){
				//swap
				int temp=arr[i];
				arr[i]=arr[pIndex];
				arr[pIndex]=temp;
				pIndex++;
			}
		}
		
		//swap the pivot element finally
		int temp=arr[pIndex];
		 arr[pIndex]=pivot;
		 arr[end]=temp;
		 
		System.out.println(Arrays.toString(arr));
		return pIndex;
	}
	
	public static int[] spiralOrder(final int[][] A) {
        int m=A.length; // num of rows;
        int n = A[0].length;//num of cols
        int[] result=new int[m*n]; //total elements
        
        int T=0;
        int B=m-1;
        int L=0;
        int R=n-1;
        int direction=0;
        int count=0;
        
        while(T<=B && L<=R){
        	System.out.println("direction is "+direction);
            if(direction==0){
            	System.out.println(count);
                for(int i =L;i<=R;i++){ 
                	System.out.println(A[T][i]);
                result[count++]=A[T][i];                
                }             
                
                System.out.println(Arrays.toString(result));
            T++;    
            }
            else if (direction ==1){
                for (int i=T;i<=B;i++){              	
                result[count++]=A[i][R];}
                System.out.println(Arrays.toString(result));   
            R--;
            }
            else if(direction ==2){
                for(int i=R;i>=L;i--){
                	
                result[count++]=A[B][i];
                }
                System.out.println(Arrays.toString(result));
            B--;    
            }else if(direction==3){
                for(int i=B;i>=T;i--){
                	
                result[count++]=A[i][L];
                }
                System.out.println(Arrays.toString(result));
            L++;
            }
            
            direction=(direction+1)%4;
        }  
        return result;
}

	public static void main(String[] args){

		
		int[][] A={{ 1, 2, 3 },{ 4, 5, 6 },{ 7, 8, 9 }};
		Arrays.toString(spiralOrder(A));
	}
}
