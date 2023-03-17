package com.lima.dto;

public class PartDetailDTO {
	private Integer id;
	private Boolean active;
	private Integer passage;
	private String question;
	private Integer questionNo;
	private String correctAnswer;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String demonstrate;
	private String photoLink;
	private String audioLink;
	private String script;

	public PartDetailDTO() {

	}

	public PartDetailDTO(Integer id, Boolean active, Integer passage, String question, Integer questionNo,
			String correctAnswer, String answer1, String answer2, String answer3, String answer4, String demonstrate,
			String photoLink, String audioLink, String script) {
		super();
		this.id = id;
		this.active = active;
		this.passage = passage;
		this.question = question;
		this.questionNo = questionNo;
		this.correctAnswer = correctAnswer;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.demonstrate = demonstrate;
		this.photoLink = photoLink;
		this.audioLink = audioLink;
		this.script = script;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Integer getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public String getDemonstrate() {
		return demonstrate;
	}

	public void setDemonstrate(String demonstrate) {
		this.demonstrate = demonstrate;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

	public String getAudioLink() {
		return audioLink;
	}

	public void setAudioLink(String audioLink) {
		this.audioLink = audioLink;
	}

	public Integer getPassage() {
		return passage;
	}

	public void setPassage(Integer passage) {
		this.passage = passage;
	}

}
