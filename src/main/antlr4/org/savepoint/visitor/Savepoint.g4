grammar Savepoint;

program
 : line+ EOF
 ;

 line
 : functionDeclaration
 | statement
 ;

statement
 : variableDeclaration';'
 | assignment';'
 | functionCall';'
 | systemFunctionCall';'
 | ifElseStatement
 | loopF
 | loopW
 | returnStatement';'
 ;

functionDeclaration
: TYPE IDENTIFIER '(' paramList? ')' functionBody ;

paramList : TYPE IDENTIFIER (',' TYPE IDENTIFIER)* ;

functionBody : '{' statement* '}' ; //TODO: cannot return from the middle of the function

returnStatement: 'return' expression? ;


variableDeclaration
: TYPE IDENTIFIER '=' expression;


assignment
 : IDENTIFIER '=' expression
 ;

functionCall
: IDENTIFIER '(' expressionList? ')';

systemFunctionCall
 : PRINT '(' expression ')'                             #printFunctionCall
 | WRITE '(' expression',' STRING ')'                   #writeFunctionCall
 | APPEND '(' expression',' STRING ')'                  #appendFunctionCall
 ;

systemFunctionCall1
: READ '(' IDENTIFIER ')'                              #readFunctionCall
;

ifElseStatement: 'if' '(' expression ')' block ('else' block)?;

block: '{' statement* '}';



constant: INTEGER | DECIMAL | BOOLEAN |STRING ;

loopF
: FORLOOP '(' (statement | IDENTIFIER';') expression';'assignment ')' block;

loopW
: LOOP '(' expression ')' block;



expressionList
: expression (',' expression)* ;

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
 | functionCall                                         #functionCallExpression
 | systemFunctionCall1                                  #systemFunctionCallExpression
 ;

booleanUnaryOp : '!' ;

booleanBinaryOp : '||' | '&&' ;

numericMultiOp : '*' | '/' | '%' ;

numericAddOp : '+' | '-' ;

booleanCompareOp: '>' | '<' | '<=' | '>=' | '==' | '!=';

stringBinaryOp : '..' ; //concat


PRINT : 'WriteLine';
READ : 'FileReadAllLines';
WRITE : 'FileWriteAllLines';
APPEND : 'FileAppendAllLines';

LOOP : 'while';
FORLOOP : 'for';
INTEGER : '-'?[0-9]+ ;
DECIMAL : '-'?[0-9]+ '.' [0-9]+ ;
BOOLEAN : 'true' | 'false' ;
STRING : ["] ( ~["\r\n\\] | '\\' ~[\r\n] )* ["] ;
TYPE : ('int' | 'double' | 'bool' | 'string');
IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]* ;



COMMENT : ( '//' ~[\r\n]* | '/*' .*? '*/' ) -> skip ;

WS : [ \t\f\r\n]+ -> skip ;