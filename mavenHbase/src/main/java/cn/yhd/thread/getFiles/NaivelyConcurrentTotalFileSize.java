package cn.yhd.thread.getFiles;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

public class NaivelyConcurrentTotalFileSize {
	private long getTotalSizeOfFilesInDir(final ExecutorService service,final File file) throws Exception{
		if (file.isFile())
			return file.length();

		long total = 0;
		final File[] children = file.listFiles();

		if (children != null) {
			final List<Future<Long>> partialTotalFutures = new ArrayList<Future<Long>>();
			for (final File child : children) {
				partialTotalFutures.add(service.submit(new Callable<Long>() {
					public Long call() throws Exception {
						return getTotalSizeOfFilesInDir(service, child);
					}
				}));
			}

			for (final Future<Long> partialTotalFuture : partialTotalFutures)
				total += partialTotalFuture.get(100, TimeUnit.SECONDS);
		}

		return total;
	}

	private long getTotalSizeOfFile(final String fileName) throws Exception {
		final ExecutorService service = Executors.newFixedThreadPool(100);
		try {
			return getTotalSizeOfFilesInDir(service, new File(fileName));
		} finally {
			service.shutdown();
		}
	}

	public static void main(final String[] args) throws Exception {
		final long start = System.nanoTime();
		final long total = new NaivelyConcurrentTotalFileSize().getTotalSizeOfFile(args[0]);
		final long end = System.nanoTime();
		System.out.println("Total Size: " + total);
		System.out.println("Time taken: " + (end - start) / 1.0e9);
	}
}