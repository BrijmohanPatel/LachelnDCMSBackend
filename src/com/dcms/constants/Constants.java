package com.dcms.constants;

public  class  Constants {

/*	public static String FILENAME = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

    public static String DEST = "E:\\Lachlen project\\Doctor_Bills\\sample_"+FILENAME+".pdf";*/
    
	public static String FILENAME;

    public static String DEST;

	public static String getFILENAME() {
		return FILENAME;
	}

	public static void setFILENAME(String fILENAME) {
		FILENAME = fILENAME;
	}

	public static String getDEST() {
		return DEST;
	}

	public static void setDEST(String dEST) {
		DEST = dEST;
	}
	
	public static String GenerateFileNameAndLocation() {
		setFILENAME(getFILENAME().replaceAll("\\.", "_"));
		setFILENAME(getFILENAME().replaceAll(" ", "_").toUpperCase());
		String FileNameAndDest = getDEST()+getFILENAME()+".pdf"; 
		return FileNameAndDest;
	}

	
}
