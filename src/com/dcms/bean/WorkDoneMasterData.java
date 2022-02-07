package com.dcms.bean;

public class WorkDoneMasterData {
			
		private String wd_id;
		private String workdone_name;
		
		
		public String getWd_id() {
			return wd_id;
		}
		public void setWd_id(String wd_id) {
			this.wd_id = wd_id;
		}
		public String getWorkdone_name() {
			return workdone_name;
		}
		public void setWorkdone_name(String workdone_name) {
			this.workdone_name = workdone_name;
		}
		@Override
		public String toString() {
			return "WorkDoneMaster [wd_id=" + wd_id + ", workdone_name=" + workdone_name + "]";
		}
		
		

}
