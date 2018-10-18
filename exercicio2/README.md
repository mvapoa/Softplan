# Descrição de mudanças da classe GeradorObservacao

Este documento contém todas as informações referentes às mudanças necessárias para a correção 
da classe GeradorObservacao, apesar da classe de testes também precisarar de refatoração, conforme
apontado pelo Sonarlint.

Logo abaixo segue a lista e correções.

## Correções

##### Inserção de pacote padrão 

Foi criado o pacote "br.com.softplan.teste" seguindo a convenção encontrada em: https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html

##### Alteração dos comentários

Foram corrigidos os caracteres fora do padrão UTF-8. Além disso, os comentários de classe foram alterados para o formato 
Javadocs. Exemplo: 

```
/**
* Gera observações, com texto pre-definido, incluindo os números, das notas fiscais, recebidos no parâmetro 
* @param listaNumeroNotasFiscais Lista contendo os números das notas fiscais
* @return Observação gerada
*/
```

##### Alterado nomes de métodos e parâmetros

Alterados os nomes dos métodos e parâmetros para condizerem com a operação que estão realizando. 

Exemplos:  
* Nome do método retornaCodigos para gerarTextoObservacoes
* Nome do parâmetro cod para textoObservacoesSB

##### Atributos removidos

Os atributos foram removidos por não terem utilidade e deixar o código sujo. O atributo
"umoNota" poderia ter sido renomeado para upercase com modificador de acesso privado, entretanto,
como o valor só se repete uma vez, o mesmo foi absorvido pelo método gerarTextoObservacoes.

#### Adição de tipo a lista de números de nota fiscal

Adicionado tipo de lista para evitar exceções de tipo inválido durante o laço de repetição em que serão extraídos os
números das notas fiscais.

#### Alterado laço utilizando Iterator

O laço utilizando Iterator foi substituído por um do/while para deixar o código mais enxuto e legível.