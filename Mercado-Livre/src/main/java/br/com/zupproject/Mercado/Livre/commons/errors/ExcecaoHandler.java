package br.com.zupproject.Mercado.Livre.commons.errors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zupproject.Mercado.Livre.commons.exceptions.EstoqueInvalidoException;
import br.com.zupproject.Mercado.Livre.commons.exceptions.PagamentoInvalidoException;

@RestControllerAdvice
public class ExcecaoHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroPadrao> tratamentoErrosArgumento(MethodArgumentNotValidException exception) {
		
		List<ErroPadrao> errosDto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroPadrao erro = new ErroPadrao(e.getField(), mensagem);
			errosDto.add(erro);
		});
		
		return errosDto;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EstoqueInvalidoException.class)
	public ErroPadrao tratamentoErroEstoqueInvalido(EstoqueInvalidoException exception) {
		return new ErroPadrao("estoque", exception.getMessage());
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PagamentoInvalidoException.class)
	public ErroPadrao tratamentoErroEstoqueInvalido(PagamentoInvalidoException exception) {
		return new ErroPadrao("pagamento", exception.getMessage());
	}
}
