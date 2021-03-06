package compilador.gals;

public interface ParserConstants {
	int START_SYMBOL = 51;

	int FIRST_NON_TERMINAL = 51;
	int FIRST_SEMANTIC_ACTION = 85;

	int[][] PARSER_TABLE = {
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, 1, 1, 1,
					1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, 2, 2, 2,
					2, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 8, 6,
					5, 4, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, 9, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, 13, 13, -1, 13, -1, -1, 12, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, 15, -1, -1, -1, -1, -1, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 18, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, 19, -1, 20, -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, 22, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, 24, -1, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, 25, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 27, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, 28, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 29, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 31, 37, -1, 30, -1, -1, -1, -1, 32, 37, -1, 33, -1, 34, 35, 36, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, 39, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1, -1, -1, -1, -1, -1, -1, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, 42, -1, 43, -1, -1, 41, -1, -1, -1, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 43, -1, -1, -1, -1, -1, -1, -1, 43, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, 44, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 44, -1, -1, -1, -1, -1, -1, -1, -1, 44, 44, 44, 44, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, 44, 44, -1, -1, 44, -1 },
			{ -1, -1, 46, -1, -1, -1, -1, -1, 45, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, 47, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 47, -1, -1, -1, -1, -1, -1, -1, -1, 47, 47, 47, 47, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, 47, 47, -1, -1, 47, -1 },
			{ -1, -1, 49, 49, -1, -1, -1, 49, 49, 48, -1, -1, -1, -1, -1, 48, 48, 48, 48, 48, -1, 49, -1, -1, -1, -1, -1, -1, -1, 49, 49, -1, 49, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, 50, -1, -1, -1, -1, -1, 51, 52, 54, 53, 55, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ -1, 56, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 56, -1, -1, -1, -1, -1, -1, -1, -1, 56, 56, 56, 56, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, 56, 56, -1, -1, 56, -1 },
			{ -1, -1, 58, 58, -1, -1, -1, 58, 58, 58, -1, -1, -1, 57, 57, 58, 58, 58, 58, 58, -1, 58, -1, -1, -1, -1, -1, -1, -1, 58, 58, -1, 58, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, 57, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 60, 59, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, -1, -1, 61, -1, -1, -1 },
			{ -1, 62, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, -1, -1, -1, -1, -1, 62, 62, 62, 62, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, 62, 62, -1, -1, 62, -1 },
			{ -1, -1, 64, 64, -1, -1, -1, 64, 64, 64, -1, 63, 63, 64, 64, 64, 64, 64, 64, 64, -1, 64, -1, -1, -1, -1, -1, -1, -1, 64, 64, -1, 64, -1, -1, -1, -1, -1, -1, 63, -1,
					-1, -1, -1, -1, -1, 64, 63, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 65, 66, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 67, -1,
					-1, -1, -1, -1, -1, -1, 68, -1, -1 },
			{ -1, 71, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 70, -1, -1, -1, -1, -1, -1, -1, -1, 73, 72, 73, 73, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, 73, 73, -1, -1, 69, -1 },
			{ -1, 74, 76, 76, -1, -1, 75, 76, 76, 76, -1, 76, 76, 76, 76, 76, 76, 76, 76, 76, -1, 76, -1, -1, -1, -1, -1, -1, -1, 76, 76, -1, 76, -1, -1, -1, -1, -1, -1, 76, -1,
					-1, -1, -1, -1, -1, 76, 76, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 78, 77, 78, 78, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, 78, 78, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 83, -1, 79, 80, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
					-1, -1, -1, 82, 81, -1, -1, -1, -1 }
	};

	int[][] PRODUCTIONS = {
			{ 28, 24, 4, 52, 5 },
			{ 53, 59, 65 },
			{ 54, 55, 56, 58, 4, 53 },
			{ 0 },
			{ 44 },
			{ 43 },
			{ 42 },
			{ 27 },
			{ 41, 7, 83, 8 },
			{ 7, 83, 8 },
			{ 0 },
			{ 24, 57 },
			{ 9, 56 },
			{ 0 },
			{ 10, 83 },
			{ 0 },
			{ 60, 4, 59 },
			{ 0 },
			{ 37, 24, 61, 63, 4, 52 },
			{ 2, 64, 56, 6, 54, 62, 3 },
			{ 0 },
			{ 4, 64, 56, 6, 54, 62 },
			{ 0 },
			{ 6, 54 },
			{ 0 },
			{ 39 },
			{ 38 },
			{ 21, 67, 66, 22 },
			{ 4, 67, 66 },
			{ 0 },
			{ 24, 69 },
			{ 65 },
			{ 29, 72, 31, 67, 68 },
			{ 32, 72, 33, 67 },
			{ 34, 2, 56, 3 },
			{ 35, 2, 70, 3 },
			{ 36, 72 },
			{ 0 },
			{ 30, 67 },
			{ 0 },
			{ 11, 72 },
			{ 7, 72, 8, 11, 72 },
			{ 2, 70, 3 },
			{ 0 },
			{ 72, 71 },
			{ 9, 72, 71 },
			{ 0 },
			{ 75, 73 },
			{ 74, 75 },
			{ 0 },
			{ 10 },
			{ 16 },
			{ 17 },
			{ 19 },
			{ 18 },
			{ 20 },
			{ 78, 76 },
			{ 77, 78, 76 },
			{ 0 },
			{ 15 },
			{ 14 },
			{ 47 },
			{ 81, 79 },
			{ 80, 81, 79 },
			{ 0 },
			{ 12 },
			{ 13 },
			{ 40 },
			{ 48 },
			{ 49, 81 },
			{ 14, 81 },
			{ 2, 72, 3 },
			{ 24, 82 },
			{ 84 },
			{ 2, 72, 71, 3 },
			{ 7, 72, 8 },
			{ 0 },
			{ 24 },
			{ 84 },
			{ 25 },
			{ 26 },
			{ 46 },
			{ 45 },
			{ 23 }
	};

	String[] PARSER_ERROR = {
			"",
			"Era esperado fim de programa",
			"Era esperado \"(\"",
			"Era esperado \")\"",
			"Era esperado \";\"",
			"Era esperado \".\"",
			"Era esperado \":\"",
			"Era esperado \"[\"",
			"Era esperado \"]\"",
			"Era esperado \",\"",
			"Era esperado \"=\"",
			"Era esperado \":=\"",
			"Era esperado \"*\"",
			"Era esperado \"/\"",
			"Era esperado \"-\"",
			"Era esperado \"+\"",
			"Era esperado \"<\"",
			"Era esperado \">\"",
			"Era esperado \"<=\"",
			"Era esperado \">=\"",
			"Era esperado \"<>\"",
			"Era esperado \"{\"",
			"Era esperado \"}\"",
			"Era esperado literal",
			"Era esperado id",
			"Era esperado num_int",
			"Era esperado num_real",
			"Era esperado caracter",
			"Era esperado programa",
			"Era esperado se",
			"Era esperado senao",
			"Era esperado entao",
			"Era esperado enquanto",
			"Era esperado faca",
			"Era esperado leia",
			"Era esperado escreva",
			"Era esperado retorne",
			"Era esperado metodo",
			"Era esperado val",
			"Era esperado ref",
			"Era esperado div",
			"Era esperado cadeia",
			"Era esperado booleano",
			"Era esperado real",
			"Era esperado inteiro",
			"Era esperado verdadeiro",
			"Era esperado falso",
			"Era esperado ou",
			"Era esperado e",
			"Era esperado nao",
			"Era esperado fim",
			"<programa> inválido",
			"<bloco> inválido",
			"<dcl_var_const> inválido",
			"<tipo> inválido",
			"<dimensao> inválido",
			"<lid> inválido",
			"<rep_lid> inválido",
			"<fator_const> inválido",
			"<dcl_metodos> inválido",
			"<dcl_metodo> inválido",
			"<par_formais> inválido",
			"<rep_par> inválido",
			"<tipo_metodo> inválido",
			"<mp_par> inválido",
			"<com_composto> inválido",
			"<replistacomando> inválido",
			"<comando> inválido",
			"<senaoparte> inválido",
			"<rcomid> inválido",
			"<lista_expr> inválido",
			"<rep_lexpr> inválido",
			"<expressao> inválido",
			"<resto_expressao> inválido",
			"<oprel> inválido",
			"<expsimp> inválido",
			"<rep_expsimp> inválido",
			"<op_add> inválido",
			"<termo> inválido",
			"<rep_termo> inválido",
			"<op_mult> inválido",
			"<fator> inválido",
			"<rvar> inválido",
			"<constante> inválido",
			"<constante_explicita> inválido"
	};
}
