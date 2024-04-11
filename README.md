# category-theory

## Monoid

Tipo de categoria onde existe apenas um elemento, o morfismo de identidade e a possibilidade de infinitos morfismos. No caso abaixo temos o monoid M=(N,factorial,id) onde: N representa o conjunto dos números naturais, factorial é a função que calcula o fatorial de um número natural e id é a função identidade. Todo morfismo deve ser fechado na categoria, ou seja, deve produzir elementos aceitos pela categoria.

- (factorial :: N ⟶ N)
- (id :: N ⟶ N)

## Functor

É a generalização do map. Aplicar uma função em uma estrutura/container de dados qualquer.

- (a -> b) -> f a -> f b

f representa a estrutura/container de dados, (a -> b) é a função que recebe valores envolvidos pelo container de dados e os mapeia para um elemento "b", em seguida o
elemento "b" é colocado em uma nova estrutura do mesmo tipo que o elemento "a" estava.

## Applicative

É uma generalização para a aplicação de functors onde a função de mapeamento (a -> b) não é limitada em apenas um argumento. Para isso duas funções são necessárias:

- pure :: a -> f a. Coloca um valor ou uma função dentro de uma estrutura/container de dados
- <*> :: f (a -> b) -> f a -> f b. Realiza a aplicação sucessiva de functors na função de mapeamento

Para ser um Applicative é necessário ser um Functor antes.
