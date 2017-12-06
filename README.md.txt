# Simulador de Investimentos

  Aplicação desenvolvida para a discilina de Projeto Interdisciplinar do III Perído do curso TI - IFPE Campus Garanhuns.


## Motivação

  - Dificuldade em encontrar simuladores que balizem em tomada de decisão no portfólio de investimentos ;


## Tipos de Investimentos:

  - Serão considerados apenas os seguintes investimentos de renda fixa:

  1. ```Poupança```;
  2. Certificado de Depósito Bancário (```CDB```);
  3. Letra de Crédito Imobilíaria (```LCI```);
  4. Letra de Crédito do Agronegócio (```LCA```);

## Documentação

  A documentação do sistema é composta do arquivo README.md (este arquivo).  


## Cálculo de Rendimentos:


   1 - ``` Poupança ```

	De acordo com o ``` Banco Central do Brasil - BCB ```, o rendimento da poupança é composto de duas parcelas:
	
	Parcela I - Remuneração dado pela ```TR``` (Taxa Referencial)
	Parcela II - Remuneração adicional que segue estes critérios:
		
		Se Taxa Selic superior a 8.5 %
			- Parcela II com taxa de 0.5 % a.m.
		Se Taxa Selic igual ou inferior a 8.5% a.a.
			- Parcela II com taxa de 70 % da Selic capitalizadas ao mês.
