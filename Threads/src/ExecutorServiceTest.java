import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ExecutorServiceTest implements Callable<String>{

	public static void main(String[] args) throws ExecutionException {
		List<Future<String>> futureList = new ArrayList<Future<String>>();
		ExecutorService executor = Executors.newFixedThreadPool(3);
		ExecutorCompletionService<String> ec = new ExecutorCompletionService<String>(executor);
		Future<String> r1 = ec.submit(new ExecutorServiceTest("wan"));
		futureList.add(r1);
		Future<String> r2 = ec.submit(new ExecutorServiceTest("jian"));
		futureList.add(r2);
		Future<String> r3 = ec.submit(new ExecutorServiceTest("guo"));
		futureList.add(r3);
		try {
			String temp = "";
			while(true){
				temp += ec.take().get();
				System.out.println(temp);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private String str;
	
	public ExecutorServiceTest(String str) {
		this.str = str;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(50);
		return str;
	}
}
