/**
 * This is a generated file. DO NOT EDIT ANY CODE HERE, YOUR CHANGES WILL BE LOST.
 */
package br.com.senior.varejo.pedidos.impl;

import java.util.function.Supplier;

import br.com.senior.messaging.ErrorCategory;
import br.com.senior.messaging.ErrorPayload;
import br.com.senior.messaging.IMessenger;
import br.com.senior.messaging.Message;
import br.com.senior.messaging.utils.DtoJsonConverter;
import br.com.senior.sdl.user.UserIdentifier;			

import br.com.senior.varejo.pedidos.*;
import java.util.concurrent.CompletableFuture;

import br.com.senior.messaging.model.ServiceContext;
import br.com.senior.messaging.model.ServiceException;
import br.com.senior.messaging.model.ServiceRunner;
import br.com.senior.varejo.pedidos.GetMetadataInput;
import br.com.senior.varejo.pedidos.GetMetadataOutput;

/**
* 
*/
public class GetMetadataImpl {

	private final Supplier<IMessenger> messengerSupplier;

	private final UserIdentifier userId;

	private final Supplier<Message> messageSupplier;

	public GetMetadataImpl(Supplier<IMessenger> messengerSupplier, UserIdentifier userId, Supplier<Message> messageSupplier) {
		this.messengerSupplier = messengerSupplier;
		this.userId = userId;
		this.messageSupplier = messageSupplier;
	}

	private Message createMessage(GetMetadataInput input) {
		if (messageSupplier != null && messageSupplier.get() != null) {
			return messageSupplier.get().followUp( //
				PedidosConstants.DOMAIN, //
				PedidosConstants.SERVICE, //
				PedidosConstants.Commands.GET_METADATA, //
				DtoJsonConverter.toJSON(input));
		}
		return new Message(userId.getTenant(), //
			PedidosConstants.DOMAIN, //
			PedidosConstants.SERVICE, //
			PedidosConstants.Commands.GET_METADATA, //
			DtoJsonConverter.toJSON(input));
	}
	
	/**
	* Chamada síncrona para o método getMetadata
	* Warning: this operation is PRIVATE and may have its behavior changed at any time without notice
	* Default 'getMetadata' query. Every service must handle this command and return metadata in the format requested. 
	* @throws PedidosMessageException quando um erro com payload for retornado pela mensageria
	*/
	public GetMetadataOutput getMetadata(GetMetadataInput input, long timeout) {
		PedidosValidator.validate(input);
		
		Message message = createMessage(input);
		Message resultMessage; 
		try {
			message.setUsername(userId.getUsername());
			resultMessage = messengerSupplier.get().requestSync(message, timeout);
			messengerSupplier.get().ack(resultMessage);
		} catch (Exception e) {
			throw new PedidosException("Erro ao enviar a mensagem", e);
		}
		
		if (resultMessage == null) {
			throw new PedidosException("Retorno inválido");
		}
		
		if (resultMessage.isError()) {
			ErrorPayload error = DtoJsonConverter.toDTO(resultMessage.getContent(), ErrorPayload.class);
			throw new PedidosMessageException(resultMessage.getErrorCategory(), error.getMessage(), error.getErrorCode());
		}
		GetMetadataOutput output = DtoJsonConverter.toDTO(resultMessage.getContent(), GetMetadataOutput.class);
		if (output == null) {
			throw new PedidosException("Contéudo do retorno inválido");
		}
		return output;
	}
	
	/**
	* Chamada assíncrona para o método getMetadata
	* Warning: this operation is PRIVATE and may have its behavior changed at any time without notice
	* Default 'getMetadata' query. Every service must handle this command and return metadata in the format requested.
	*/
	public void getMetadata(GetMetadataInput input) {
		PedidosValidator.validate(input);
		
		Message message = createMessage(input);
		try {
			message.setUsername(userId.getUsername());
			messengerSupplier.get().send(message);
		} catch (Exception e) {
			throw new PedidosException("Erro ao enviar a mensagem", e);
		}
	}
	
	/**
	* Chamada assíncrona para o método getMetadata
	* Warning: this operation is PRIVATE and may have its behavior changed at any time without notice
	* Default 'getMetadata' query. Every service must handle this command and return metadata in the format requested.
	*/
	public CompletableFuture<GetMetadataOutput> getMetadataRequest(GetMetadataInput input) {
		PedidosValidator.validate(input);
	
		if (ServiceContext.get() == null) {
			throw new ServiceException(ErrorCategory.INTERNAL_ERROR, "Service Context não iniciado. Erro ao efetuar request da mensagem");
		}
		ServiceRunner serviceRunner = ServiceContext.get().getCurrentServiceRunner();
		Message message = createMessage(input);
		message.setUsername(userId.getUsername());
		return serviceRunner.request(message, GetMetadataOutput.class);
	}
	
}
