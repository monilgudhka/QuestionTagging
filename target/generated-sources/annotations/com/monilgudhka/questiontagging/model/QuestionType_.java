package com.monilgudhka.questiontagging.model;

import com.monilgudhka.questiontagging.model.Category;
import com.monilgudhka.questiontagging.model.Keyword;
import com.monilgudhka.questiontagging.model.Question;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-02T18:02:24")
@StaticMetamodel(QuestionType.class)
public class QuestionType_ { 

    public static volatile SingularAttribute<QuestionType, String> questionTypeName;
    public static volatile SetAttribute<QuestionType, Question> questionSet;
    public static volatile SingularAttribute<QuestionType, String> description;
    public static volatile SingularAttribute<QuestionType, Long> id;
    public static volatile SingularAttribute<QuestionType, Category> category;
    public static volatile SetAttribute<QuestionType, Keyword> keywordSet;

}