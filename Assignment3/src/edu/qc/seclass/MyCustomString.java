package edu.qc.seclass;

public class MyCustomString implements MyCustomStringInterface{
    String myString;
    public MyCustomString(){
        this.myString = null;
    }
    @Override
    public String getString(){
        if (this.myString.equals(null)) return null;
        return this.myString;
    }
    @Override
    public void setString(String string){
        this.myString = string;
    }
    @Override
    public int countNumbers(){
        if(this.myString.equals(null)){
            throw new NullPointerException("null");
        }

        int c=0;
        boolean notLast = true;
        for(int i = 0; i < this.myString.length(); i++){
            if(Character.isDigit(this.myString.charAt(i))){
                if(notLast){
                    c++;
                    notLast = false;
                }
            }else{notLast = true;}
        }
        return c;
    }
    @Override
    public String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd){
        if (this.myString.equals(null)){
            throw new NullPointerException("null");
        }
        if(n<=0) throw new IllegalArgumentException("n<0");
        if(this.myString.length() < n) return "";

        String str = "";
        char [] chArray = this.myString.toCharArray();
        if(startFromEnd){
            for(int i = this.myString.length() % n; i < this.myString.length(); i+=n){
                str += chArray[i];
            }
        }
        else{
            for(int i = n-1; i < this.myString.length(); i+=n){
                str += chArray[i];
            }
        }
        return str;


    }


    public void convertDigitsToNamesInSubstring(int startPosition, int endPosition){
        if(startPosition > endPosition){
            throw new IllegalArgumentException("Try again");
        }
        if (startPosition > this.myString.length() || startPosition <1){
            throw new MyIndexOutOfBoundsException("OOB");
        }
        if (endPosition > this.myString.length() || endPosition < 1){
            throw new MyIndexOutOfBoundsException("OOB");
        }
        if((startPosition > 0 || endPosition > 0) && this.myString == null){
            throw new NullPointerException("null");
        }

        int digit  = 0;
        String ans = "";
        String nameOfDigit = "";

        char [] chArray = this.myString.toCharArray();

        for (int i = 0; i < startPosition -1; i++){
            ans += chArray[i];
        }
        for(int i = startPosition -1; i< endPosition; i++){
            if(Character.isDigit(this.myString.charAt(i))){
                digit = Character.getNumericValue((this.myString.charAt(i)));

                if(digit == 0) nameOfDigit = "Zero";
                if(digit == 1) nameOfDigit = "One";
                if(digit == 2) nameOfDigit = "Two";
                if(digit == 3) nameOfDigit = "Three";
                if(digit == 4) nameOfDigit = "Four";
                if(digit == 5) nameOfDigit = "Five";
                if(digit == 6) nameOfDigit = "Six";
                if(digit == 7) nameOfDigit = "Seven";
                if(digit == 8) nameOfDigit = "Eight";
                if(digit == 9) nameOfDigit = "Nine";
                ans += nameOfDigit;
            }
            else ans += chArray[i];

        }
        for(int i = endPosition; i < this.myString.length(); i++){
            ans += chArray[i];
        }
        this.setString(ans);




    }












}