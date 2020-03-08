package com.springbootbatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="writer")
public class WriterSO {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	
	@Column(name="full_name")
    private String fullName;
    
	@Column(name="random_num")
	private String randomNum;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(String randomNum) {
        this.randomNum = randomNum;
    }

    @Override
    public String toString() {
        return "WriterSO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", randomNum='" + randomNum + '\'' +
                '}';
    }
}
