grammar Savepoint;

program
 : statement+ EOF
 ;

statement
 : assignment';'
 | systemFunctionCall';'
 | variableDeclaration';'
 | integerDeclaration';'
 | stringDeclaration';'
 | decimalDeclaration';'
 | boolDeclaration';'
 | ifElseStatement
 | returnStatement';'
 ;

assignment
 : IDENTIFIER '=' expression
 ;

systemFunctionCall
 : PRINT '(' expression ')'                             #printFunctionCall
 ;

 variableDeclaration
 : 'var' IDENTIFIER '=' expression
 ;

 integerDeclaration
 : 'int' IDENTIFIER '=' INTEGER
 ;

 stringDeclaration
 : 'string' IDENTIFIER '=' STRING
 ;

decimalDeclaration
: 'double' IDENTIFIER '=' DECIMAL
;

boolDeclaration
: 'bool' IDENTIFIER '=' BOOLEAN
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

IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]* ;

COMMENT : ( '//' ~[\r\n]* | '/*' .*? '*/' ) -> skip ;

WS : [ \t\f\r\n]+ -> skip ;