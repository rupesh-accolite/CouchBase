package com.example.couchBase;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.couchbase.client.core.retry.FailFastRetryStrategy;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;



@Component
public class CouchBasePersonScheduler {
	
	private static long fileCount = 1;
	
	@Scheduled(cron = "*/30 * * * * *")
	public void createExcelSheet()  throws Exception  {
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("Person Information");
		XSSFRow row;
		row = spreadsheet.createRow(0);
		row.createCell(0).setCellValue("NAME");
		row.createCell(1).setCellValue("AGE");
		row.createCell(2).setCellValue("COMPANY");
		row.createCell(3).setCellValue("COMPANY ID");
		
		final CouchbaseEnvironment env = DefaultCouchbaseEnvironment
		           .builder().connectTimeout(50000)
		           .retryStrategy(FailFastRetryStrategy.INSTANCE)
		           .build();
		CouchbaseCluster cluster = CouchbaseCluster.create(env,"127.0.0.1");
		cluster.authenticate("rupesh","rupesh");
		Bucket bucket = cluster.openBucket("example");
		
		String query = "SELECT * FROM example";
		N1qlQueryResult result = bucket.query(N1qlQuery.simple(query));
		int i=1;
		for (N1qlQueryRow n1qlQueryRow : result.allRows()) {
			row = spreadsheet.createRow(i);
			row.createCell(0).setCellValue(n1qlQueryRow.value().getObject("example").getString("name"));
			row.createCell(1).setCellValue(n1qlQueryRow.value().getObject("example").getString("age"));
			row.createCell(2).setCellValue(n1qlQueryRow.value().getObject("example").getString("company"));
			row.createCell(3).setCellValue(n1qlQueryRow.value().getObject("example").getString("companyId"));
			i++;
		}
		
		FileOutputStream out = new FileOutputStream(new File("F://CouchBaseExcelFiles/File"+fileCount+".xlsx"));
	    fileCount++;
        workbook.write(out);
        out.close();
		
	}

}
