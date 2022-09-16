package kr.or.connect.booking.dto;

public class ImgFile {
	private int id;
	private String file_name;
	private String save_file_name;
	private String content_type;
	private int delete_flag;
	private String create_date;
	private String modify_date;
	
	
	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	@Override
	public String toString() {
		return "ImgFile [id=" + id + ", file_name=" + file_name + ", save_file_name=" + save_file_name
				+ ", content_type=" + content_type + ", delete_flag=" + delete_flag + ", create_date=" + create_date
				+ ", modify_date=" + modify_date + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getSave_file_name() {
		return save_file_name;
	}

	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	
	

}
