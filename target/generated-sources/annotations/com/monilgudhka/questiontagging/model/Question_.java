package com.monilgudhka.questiontagging.model;

import com.monilgudhka.questiontagging.model.Concept;
import com.monilgudhka.questiontagging.model.CongnitiveLevel;
import com.monilgudhka.questiontagging.model.DifficultyLevel;
import com.monilgudhka.questiontagging.model.QuestionBank;
import com.monilgudhka.questiontagging.model.QuestionType;
import com.monilgudhka.questiontagging.model.WHType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-02T18:02:24")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, CongnitiveLevel> congnitiveLevel;
    public static volatile SingularAttribute<Question, DifficultyLevel> difficultyLevel;
    public static volatile SingularAttribute<Question, QuestionBank> questionBank;
    public static volatile SetAttribute<Question, Concept> concept;
    public static volatile SingularAttribute<Question, WHType> whType;
    public static volatile SingularAttribute<Question, Long> id;
    public static volatile SingularAttribute<Question, QuestionType> questionType;
    public static volatile SingularAttribute<Question, String> questionText;

}