
public class AtmoicTest {
	
	private long b = 0;
	 
    public synchronized void set1() {
        b = 0;
    }
 
    public synchronized void set2() {
        b = -1;
    }
 
    public synchronized void check() {
        System.out.println(b);
        if (0 != b && -1 != b) {
            System.err.println("Error");
        }
    }
	public static void main(String[] args) {
		
		final AtmoicTest v = new AtmoicTest();
		 
	    // 线程 1：设置 b = 0
	    final Thread t1 = new Thread() {
	        public void run() {
	            while (true) {
	                v.set1();
	            }
	        };
	    };
	    t1.start();
	 
	    // 线程 2：设置 b = -1
	    final Thread t2 = new Thread() {
	        public void run() {
	            while (true) {
	                v.set2();
	            }
	        };
	    };
	    t2.start();
	 
	    // 线程 3：检查 0 != b && -1 != b
	    final Thread t3 = new Thread() {
	        public void run() {
	            while (true) {
	                v.check();
	            }
	        };
	    };
	    t3.start();
	}

}
