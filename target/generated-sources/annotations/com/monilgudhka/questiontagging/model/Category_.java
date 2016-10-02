package com.monilgudhka.questiontagging.model;

import com.monilgudhka.questiontagging.model.QuestionType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-02T18:02:24")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile SetAttribute<Category, QuestionType> questionTypeSet;
    public static volatile SingularAttribute<Category, Long> id;
    public static volatile SingularAttribute<Category, String> categoryName;

}