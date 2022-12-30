package com.lima.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "part_detail")
public class PartDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Boolean active;
	private String question;
	private Integer questionNo;
	private String correctAnswer;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String demonstrate;
	private String photoName;
	private String linkAudio;

	@ManyToOne
	@JoinColumn(name = "part_id")
	private Part part;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getLinkAudio() {
		return linkAudio;
	}

	public void setLinkAudio(String linkAudio) {
		this.linkAudio = linkAudio;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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

	public Integer getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

}
