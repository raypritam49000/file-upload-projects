package com.aggregation.rest.api.utils.search.filters;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;

public class Specifications {
    public static Criteria includesMatchText(String textToMatch, String path) {
        if (textToMatch == null || textToMatch.equals("")) {
            return null;
        }
        return Criteria.where(path).regex(textToMatch);
    }


    public static Criteria exactMatchText(String textToMatch, String path) {
        if (textToMatch == null || textToMatch.equals("")) {
            return null;
        }
        return Criteria.where(path).is(textToMatch);
    }

    public static Criteria exactMatchText(ObjectId textToMatch, String path) {
        if (textToMatch == null) {
            return null;
        }
        return Criteria.where(path).is(textToMatch);
    }


    public static Criteria exactMatchWithinDocuments(String textToMatch, String path) {
        if (textToMatch == null || textToMatch.equals("")) {
            return null;
        }
        return Criteria.where(path).in(textToMatch);
    }



}
