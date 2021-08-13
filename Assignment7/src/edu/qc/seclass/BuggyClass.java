package edu.qc.seclass;

public class BuggyClass {

    public int buggyMethod1(int a, int b){
        int ans = -1;
        if(a > b) {
            ans = a / b;
        }
        if(a < b) {
            ans = b / a;
        }
        if(a == b) {
            ans = 1;
        }
        return ans;
    }

    public int buggyMethod2(int a, int b) {
        int ans = a + b;
        if(a > b) {
            ans = a*b;
        }
        if(a < b) {
            ans = a + b;
        }
        return ans/a;
    }

    public int buggyMethod3(int a){
        int ans = a;
        if(ans % 10 == 0) {
            ans = a /10;
        }else if(ans % 15 == 0) {
            ans+= 2;
        }else if(ans != 11) {
            ans = a /(a - 4);
        }
        return ans;
    }

    public void buggyMethod4() {
		/*
		It is not possible to create a test suite that achieves 100% branch coverage and does not reveal the fault
		but does so when there is a 100% statement coverage. This is not possible since branch coverage covers more than statement coverage therefore
		it is not possible to create a test that achieves 100% branch coverage but does not reveal the fault.
		*/
    }

    public void buggyMethod5() {
        /*
         * It is not possible since statement coverage will go through line 4 and realize that it is dividing by zero and throw an exception.
         */
    }

}
