grammar Savepoint;

program
 : statement+ EOF
 ;

statement
 : assignment';'
 | systemFunctionCall';'
 | ifElseStatement
 | returnStatement';'
 ;

assignment
 : TYPE IDENTIFIER '=' expression
 ;

systemFunctionCall
 : PRINT '(' expression ')'                             #printFunctionCall
 ;


ifElseStatement: 'if' '(' expression ')' block 'else' block;

block: '{' statement* '}';

returnStatement: 'return' expression? ;

constant: INTEGER | DECIMAL | BOOLEAN |STRING ;

expression
 : constant                                             #constantExpression
 | IDENTIFIER                                           #identifierExpression
 | '(' expression ')'                                   #parenthesesExpression
 | booleanUnaryOp expression                            #booleanUnaryOpExpression
 | expression booleanBinaryOp expression                #booleanBinaryOpExpression
 | expression numericMultiOp expression                 #numericMultiOpExpression
 | expression numericAddOp expression                   #numericAddOpExpression
 | expression stringBinaryOp expression                 #stringBinaryOpExpression
 | expression booleanCompareOp expression               #comparisonExpression
 ;

booleanUnaryOp : '!' ;

booleanBinaryOp : '||' | '&&' ;

numericMultiOp : '*' | '/' | '%' ;

numericAddOp : '+' | '-' ;

booleanCompareOp: '>' | '<' | '<=' | '>=' | '==' | '!=';

stringBinaryOp : '..' ; //concat


PRINT : 'WriteLine';

INTEGER : '-'?[0-9]+ ;
DECIMAL : '-'?[0-9]+ '.' [0-9]+ ;
BOOLEAN : 'true' | 'false' ;
STRING : ["] ( ~["\r\n\\] | '\\' ~[\r\n] )* ["] ;
TYPE : ('int' | 'double' | 'bool' | 'string');
IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]* ;



COMMENT : ( '//' ~[\r\n]* | '/*' .*? '*/' ) -> skip ;

WS : [ \t\f\r\n]+ -> skip ;