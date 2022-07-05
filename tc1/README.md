# Anotações

* proposta ✔
  * contextualização: pq vai falar de banco de dados sql, dna...?
    * mercado: volume aumentando exp
    * problemas computacionais com grande volume de dados relacionados
    * novos bancos de dados
    * ranking
    * problema app baseado em dna em recuperação de dados
      * quero seq 51 do dna
      * bloco 1.000.000, cadeixa xyz
  * motivação:
    * panorama de bancos, e apresentar quais bancos tem melhor performance em cima de uma app
  * objetivos: elaborar análise para interpretar qual
  * metodologia
* documento escolha orientador ✔
* monografia ✔ (https://www.overleaf.com/project/605bd83d007f81f9e1f754ad)
  0. resumo:
    * 5 palavras-chave: contexto, problema, solução, método proposto, conclusão
  1. introdução: ✔
    1.1 contexto
    1.2 definição do problema
    1.3 objetivos
      * Sempre com verbo
      * Objetivo Geral
      * Objetivos específicos (até 5): não confundir objetivos específicos com etapas do TCC! Aqui são considerados subprodutos
    1.4 justificativa
    1.5 delimitação de estudo: delimitar limites
    1.6 organização da monografia/artigo: rotular oq cada capítulo vai fazer
  2. referencial teórico ✔
    * busca de sequenciamento de dna
      * dna como está estruturado
      * imagens oq é genoma humano
      * falad de banco de dados genoma (.. no eua)
      * +pesquisa +sequencia +[dna,proteina]
    * modelos de dados
      * falar sobre os modelos sgbd tb
      * além desses modelos, existem os modelos (oo, hierarquico, semi-estruturado...) e eles n servem pq...
  3. trabalhos relacionados/correlatos ✔
    * planejamento de como fazer os experimentos
    * variam quantidades inserção tuplas
      * volumes (cenário 1, 1k, 1m, 1b...)
    * 1º repetir os esperimentos baseado em um conjunto de artigos, 2º criar análises práticas de crud
    * regra busca
    * tópicos possíveis
      * NGSmethDB: uma base de dados mongoDB de metilação (grupo metil) derivada de dados NGS (Next-Generation Sequencing) 
      * Gigwa: web-based tool for easy exploring large amounts of genotypes data
    * tabela 1 = perguntas
      * vertente algoritmo mais usado (confiável vs rápido)
        | Referência              | Perguntas                                                                                         |
        |-------------------------|---------------------------------------------------------------------------------------------------|
        | Perguntas Gerais        |                                                                                                   |
        | GQ1                     | Quais estratégias tem sido adotadas para melhorar o desempenho da pesquisa de sequências de DNA?  |
        | GQ2                     | Quais os algoritmos mais utilizados na pesquisa de sequências de DNA?                             |
        | GQ3                     | Quais os modelos SGDBs mais usados em BDG?                                                        |
        | Perguntas Focadas       |                                                                                                   |
        | FQ1                     | Qual SGBD tem sido mais utilizado no manuseio de sequências de DNA?                               |
        | FQ2                     | Quais as métricas de desempenho apresentadas na preparação de dados de sequências de DNA?         |
        | FQ3                     | Quais as métricas de desempenho apresentadas na recuperação de dados de sequências de DNA?        |
        | Perguntas Estatísticas  |                                                                                                   |
        | EQ1                     | Onde as pesquisas foram publicadas?                                                               |
        | EQ2                     | Qual o número de publicações por ano?                                                             |
      * tabela 2 - texto de busca
        | Termos Principais       | Termos de pesquisa                                                                                |
        |-------------------------|---------------------------------------------------------------------------------------------------|
        | BDG                     | ((SQL OR NoSQL OR NewSQL) AND genome) OR                                                          |
        |                         | (("genome search" OR "DNA search") AND algorithm)                                                 |
      * tabela 3 - criétrios de inclusão e exclusão
        | Critério                | Descrição                                                                                         |
        |-------------------------|---------------------------------------------------------------------------------------------------|
        | Critérios de inclusão                                                                                                       |
        |-------------------------|---------------------------------------------------------------------------------------------------|
        | CI1                     | O artigo deve estar em artigos publicados em periódicos                                           |
        | CI2                     | O artigo deve ser completo                                                                        |
        | CI3                     | O artigo deve estar em inglês                                                                     |
        |-------------------------|---------------------------------------------------------------------------------------------------|
        | Critérios de exclusão                                                                                                       |
        |-------------------------|---------------------------------------------------------------------------------------------------|
        | CE1                     | Os artigos publicados antes de 2016 e após 2021                                                   |
        | CE2                     | Os artigos não relacionados a BDG                                                                 |
        | CE3                     | Os artigos não relacionados às perguntas                                                          |
        | CE4                     | Os artigos duplicados                                                                             |
        | CE5                     | Os artigos não passíveis a acesso                                                                 |
        - foco em conversão genoma (single-cell genomics, microarrays, genome sequencing, metagenomic sequencing, RNA-seq)
        - gene expression
        - string similarity search
        - post-processing software
        - dados não relacionados com sequência de genoma
          - dados clínicos/médicos (laudos...)
          - dados farmacêuticos em escala superior a genoma (celular, química, produto...)
          - dados de anotações de genoma
          - dados de agricultura
        - segurança de dados
        - integração de bancos de dados
          - integração de bancos de dados heterogêneos em estrutura (genoma + documentos)
        - sem foco sobre pesquisa sobre dados de genoma
          - pesquisa sobre pesquisas em genoma (artigos...)
          - estudo acerca de tópicos em escala celular ou maior, sem pesquisa
        - pesquisa sobre dados de genoma sem foco em melhorar desempenho
      * Etapas
        * query search
        * impurity removal
        * filter by title
        * filter by abstract
        * combination
        * remove duplicates
        * filter by three-pass based method
     * período 2016 - 2021
     * +NGSmethDB: sgbd mongo de metilação de dados ngs
  4. metodologias de pesquisa ou materiais e métodos ✔
    * que bancos vamos usar
    * qual tipo cenário
    * regra busca
    * usar 6-7 bases
  5. protótipo e tecnologias utilizadas ✖
  6. apresentação e análise de dados
  7. conclusão
  8. referências, abstract, keywords, apêndices
* Orientações ABNT
  * http://unisinos.br/biblioteca/images/abnt/2021-1/MANUAL_ABNT_BIBLIOTECA_JANEIRO_2021-1.pdf
* Métodos de Pesquisa
  * http://www.ufrgs.br/cursopgdr/downloadsSerie/derad005.pdf
  * https://tsxvpsbr.dyndns.org/arquivos/UFFS/Metodologia%20De%20Pesquisa%20CienciaDaComputacao%20_%20TCC1.pdf

## TODO

* Mover artigos para mendeley