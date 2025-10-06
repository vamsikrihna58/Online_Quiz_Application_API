package com.Online_Quiz_Application_API.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "options")
public class OptionsEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    @Column(name = "`option_id`")
	private int option;
	@Column(name = "`answer`")
	private String answer;
	@Column(name = "correct_Or_Not")
	private boolean correct_Or_Not;
	@ManyToOne
	@JoinColumn(name = "questions_id", referencedColumnName = "id")
	 @JsonBackReference(value = "question-option")
	private Questions questions;

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isCorrect_Or_Not() {
		return correct_Or_Not;
	}

	public void setCorrect_Or_Not(boolean correct_Or_Not) {
		this.correct_Or_Not = correct_Or_Not;
	}

}
