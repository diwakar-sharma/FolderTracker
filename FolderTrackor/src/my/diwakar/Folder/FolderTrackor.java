package my.diwakar.Folder;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.prorigo.Logger;

 class FolderTrackor {

	public static void folderTrackor(String[] args) throws Exception {
		for(;;){
			 WatchService watchService = FileSystems.getDefault().newWatchService();
		   // System.out.println("START MONITORING  **************");
		    for (int i = 1; i < args.length; i++) {
				Path folder = Paths.get(args[i]);
				WatchKey watchKey = folder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
			
			 }

		    boolean valid = true;
		    WatchKey watchKey = watchService.take();
		    for (WatchEvent<?> event : watchKey.pollEvents()) {
		        WatchEvent.Kind kind = event.kind();
		        if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
		            String fileName = event.context().toString();
		            String folder = "";
		            for (int i = 1; i < args.length; i++) {
		            	File file = new File(args[i]+"\\"+fileName);
						if(file.exists()) {
							folder = args[i];
				            System.out.println((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date())+"\t"+fileName+"\t"+folder);
							break;
						}
					 }
		        }
		    }

		}
		
		
	}

	public static void createDir(String dirName) throws Exception
	{
		try 
		{
			boolean exists = (new java.io.File(dirName)).exists();
			if (!exists) {
				boolean success = (new java.io.File(dirName)).mkdirs();
			}
		}catch(Exception e){
			throw e;
		}
	}

	public static String getLogPath(String folder) throws Exception{
		createDir(folder);
		String logPath =  folder+"/FolderTrackor_"+(new SimpleDateFormat("yyyyMMdd")).format(new Date())+".log";
		File logSS=new File(logPath);
		if(!logSS.exists())
			logSS.createNewFile();
		return logPath;
	}
    public static void main(String[] args)
    {
    	try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			String strDate = formatter.format(date);
			String userName = null;
			System.out.println(" ::::::::::::::::: Commodity Parts ::::::::::::: user :::: "+userName+" , Date ::::::::::: "+strDate);
			
    		Logger.println("D:\\Logs\\plmportal\\userdetails.txt", "\"Commodity Parts\",\""+userName+"\",\""+strDate+"\"");
    		folderTrackor(args);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
