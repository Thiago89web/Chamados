package com.thiago.chamado.entity.response;

public class ResponseMessage {

	private String message;
	private Long idFile;

	public ResponseMessage(String message, Long idFile) {
		this.message = message;
		this.idFile = idFile;
	}
	
	public ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getIdFile() {
		return idFile;
	}

	public void setIdFile(Long idFile) {
		this.idFile = idFile;
	}

}
