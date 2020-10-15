# Unidade 1 - Exercício 14 - Java
Esse exercício foi escrito em Java e testado com Gradle ou Maven/jUnit.

Crie mais casos de teste e os faça passar para os seguintes cenários:

 - Venda sem itens - o cupom fiscal não pode ser impresso
 - Venda com dois itens diferentes apontando para o mesmo produto - lança erro ao adicionar o item com produto repetido
 - Item de Venda com quantidade zero ou negativa - não pode ser adicionado na venda
 - Produto com valor unitário zero ou negativo - item não pode ser adicionado na venda com produto nesse estado

### Comando para execução
`gradle test`
ou
`mvn test`
