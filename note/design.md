
```sequence

title:login sample

participant user        as A
participant filter      as B
participant controller  as C
participant service     as 
D

A->B:password
A->B:查询
B->B:查询DB
B-->A:返回
A->A:获取其他信息
A-->网页:返回
```

<!--```flow
st=>start: Start
op=>operation: Your Operation
cond=>condition: Yes or No?
e=>end
st->op->cond
cond(yes)->e
cond(no)->op
```-->
