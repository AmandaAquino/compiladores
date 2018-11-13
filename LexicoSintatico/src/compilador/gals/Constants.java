package compilador.gals;

public interface Constants extends ScannerConstants, ParserConstants
{
    int EPSILON  = 0;
    int DOLLAR   = 1;

    int t_TOKEN_2 = 2; //"("
    int t_TOKEN_3 = 3; //")"
    int t_TOKEN_4 = 4; //";"
    int t_TOKEN_5 = 5; //"."
    int t_TOKEN_6 = 6; //":"
    int t_TOKEN_7 = 7; //"["
    int t_TOKEN_8 = 8; //"]"
    int t_TOKEN_9 = 9; //","
    int t_TOKEN_10 = 10; //"="
    int t_TOKEN_11 = 11; //":="
    int t_TOKEN_12 = 12; //"*"
    int t_TOKEN_13 = 13; //"/"
    int t_TOKEN_14 = 14; //"-"
    int t_TOKEN_15 = 15; //"+"
    int t_TOKEN_16 = 16; //"<"
    int t_TOKEN_17 = 17; //">"
    int t_TOKEN_18 = 18; //"<="
    int t_TOKEN_19 = 19; //">="
    int t_TOKEN_20 = 20; //"<>"
    int t_TOKEN_21 = 21; //"{"
    int t_TOKEN_22 = 22; //"}"
    int t_literal = 23;
    int t_id = 24;
    int t_num_int = 25;
    int t_num_real = 26;
    int t_caracter = 27;
    int t_programa = 28;
    int t_se = 29;
    int t_senao = 30;
    int t_entao = 31;
    int t_enquanto = 32;
    int t_faca = 33;
    int t_leia = 34;
    int t_escreva = 35;
    int t_retorne = 36;
    int t_metodo = 37;
    int t_val = 38;
    int t_ref = 39;
    int t_div = 40;
    int t_cadeia = 41;
    int t_booleano = 42;
    int t_real = 43;
    int t_inteiro = 44;
    int t_verdadeiro = 45;
    int t_falso = 46;
    int t_ou = 47;
    int t_e = 48;
    int t_nao = 49;
    int t_fim = 50;

}
