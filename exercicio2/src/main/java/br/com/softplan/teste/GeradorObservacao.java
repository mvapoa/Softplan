package br.com.softplan.teste;

import java.util.Iterator;
import java.util.List;

public class GeradorObservacao {

    /**
     * Gera observações se a quantidade de números de nota fiscal fornecidos forem maior que 0
     *
     * @param listaNumeroNotasFiscais Lista contendo os números das notas fiscais
     * @return Observação gerada
     */
    public String geraObservacao(List<Integer> listaNumeroNotasFiscais) {
        if (!listaNumeroNotasFiscais.isEmpty()) {
            return constroiTextoObservacoes(listaNumeroNotasFiscais);
        }
        return "";
    }

    /**
     * Gera observações, com texto pre-definido, incluindo os números, das notas fiscais, recebidos no parâmetro
     *
     * @param listaNumeroNotasFiscais Lista contendo os números das notas fiscais
     * @return Observação gerada
     */
    private String constroiTextoObservacoes(List<Integer> listaNumeroNotasFiscais) {

        StringBuilder textoObservacoesSB = new StringBuilder();
        String observacoesPrefixo;

        //Adiciona texto base no Stringbuilder
        if (listaNumeroNotasFiscais.size() >= 2) {
            observacoesPrefixo = "Fatura das notas fiscais de simples remessa: ";
        } else {
            observacoesPrefixo = "Fatura da nota fiscal de simples remessa: ";
        }

        //Adiciona valores da nota fiscal
        Iterator<Integer> iterator = listaNumeroNotasFiscais.iterator();

        do {
            Integer c = iterator.next();
            //Reduzida quantidade de ifs. Se existir só um valor na lista, retornar só o código, caso contrário retorna
            //no formato x,y e z
            if (iterator.hasNext() && textoObservacoesSB.length() > 0) {
                textoObservacoesSB.append(", ");
            } else if (textoObservacoesSB.length() > 0) {
                textoObservacoesSB.append(" e ");
            }
            textoObservacoesSB.append(c);
        } while (iterator.hasNext());

        return observacoesPrefixo + textoObservacoesSB.append(".").toString();
    }
}