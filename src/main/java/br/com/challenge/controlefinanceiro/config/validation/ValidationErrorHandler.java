package br.com.challenge.controlefinanceiro.config.validation;

import br.com.challenge.controlefinanceiro.config.validation.ErroFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    //Essa classe serve como interceptador para quando ocorrer um erro chama essa classe, passando o erro que aconteceu
    //Passei nessa classe um método para simplificar a mensagem de retorno do erro da requisição

    @Autowired
    private MessageSource messageSource; //ajuda a pegar mensagens de erro

    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //informa o retorno http da requisição (no caso o erro 400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormDTO> handle(MethodArgumentNotValidException exception){
        List<ErroFormDTO> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        //getBindingResult - pega o resultado das validações
        //getFieldError - pega todos os erros de formulário que aconteceram

        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale()); //LocaleContextHolder.getLocale() - pega a mensagem da requisição no idioma correto
            ErroFormDTO erro = new ErroFormDTO(e.getField(), mensagem);
            dto.add(erro);
        });
        return dto;
    }
}
