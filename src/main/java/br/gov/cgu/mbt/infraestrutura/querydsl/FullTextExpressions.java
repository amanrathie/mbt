package br.gov.cgu.mbt.infraestrutura.querydsl;

import com.querydsl.core.types.dsl.BooleanTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;

import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public final class FullTextExpressions {

    private static final String TOKEN_FULL_TEXT = "\"%s*\"";

    private FullTextExpressions() {/* Hide do construtor de casse utilitária */ }

    public static BooleanTemplate contains(StringPath path, String containsSearchCondition) {
        return Expressions.booleanTemplate("contains({0}, {1})", path, toFullTextSyntax(containsSearchCondition));
    }

    private static String toFullTextSyntax(String search) {
        return asList(search.split(" "))
                .stream()
                .map(token -> String.format(TOKEN_FULL_TEXT, token))
                .collect(Collectors.joining(" and "));
    }

}
