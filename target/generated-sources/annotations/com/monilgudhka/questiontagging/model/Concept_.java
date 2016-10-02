package com.monilgudhka.questiontagging.model;

import com.monilgudhka.questiontagging.model.DomainInfo;
import com.monilgudhka.questiontagging.model.Question;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-02T18:02:24")
@StaticMetamodel(Concept.class)
public class Concept_ { 

    public static volatile SingularAttribute<Concept, String> conceptName;
    public static volatile SingularAttribute<Concept, DomainInfo> domainInfo;
    public static volatile SetAttribute<Concept, Question> question;
    public static volatile SingularAttribute<Concept, Long> id;

}