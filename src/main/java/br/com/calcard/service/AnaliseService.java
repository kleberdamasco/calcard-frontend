package br.com.calcard.service;

import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.calcard.dto.ClienteDTO;
import br.com.calcard.dto.PropostaResultDTO;

@Service
public class AnaliseService {

	private final static String URL_BASE = "http://localhost:8082/cliente";
	private final static String ANALISAR_PATH = "/analisar";
	private final static String PESQUISAR_PATH = "/consultar/";

	public PropostaResultDTO analisar(ClienteDTO clienteDTO) throws Exception {

		try (CloseableHttpClient httpClient = HttpClients.createMinimal()) {
			
			RequestConfig requestConfig = RequestConfig.custom() //
					.setSocketTimeout(120_000) //
					.setConnectTimeout(30_000) //
					.build();
			
			HttpUriRequest post = RequestBuilder.post() //
					.setUri(URL_BASE + ANALISAR_PATH) //
					.addHeader("Content-Type", "application/json") //
					.setCharset(StandardCharsets.UTF_8) //
					.setEntity(new StringEntity(new Gson().toJson(clienteDTO), StandardCharsets.UTF_8.name())) //
					.setConfig(requestConfig) //
					.build();
			
			CloseableHttpResponse response = null;
			try {
				response = httpClient.execute(post);
				String messageBody = null;
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					messageBody = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8.name());
					EntityUtils.consume(entity);
				}
				return new Gson().fromJson(messageBody, PropostaResultDTO.class);
			} finally {
				if (response != null) {
					response.close();
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public PropostaResultDTO consultarProposta(ClienteDTO clienteDTO) throws Exception {
		
		try (CloseableHttpClient httpClient = HttpClients.createMinimal()) {
			
			RequestConfig requestConfig = RequestConfig.custom() //
					.setSocketTimeout(120_000) //
					.setConnectTimeout(30_000) //
					.build();
			
			HttpUriRequest get = RequestBuilder.get() //
					.setUri(URL_BASE + PESQUISAR_PATH + clienteDTO.getCpf()) //
					.addHeader("Content-Type", "application/json") //
					.setCharset(StandardCharsets.UTF_8) //
					.setConfig(requestConfig) //
					.build();
			
			CloseableHttpResponse response = null;
			try {
				response = httpClient.execute(get);
				String messageBody = null;
				HttpEntity entity = response.getEntity();
				
				if (entity != null) {
					messageBody = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8.name());
					EntityUtils.consume(entity);
					return new Gson().fromJson(messageBody, PropostaResultDTO.class);
				} else {
					return new PropostaResultDTO("Cliente nao cadastrado", "");
				}
				
			} finally {
				if (response != null) {
					response.close();
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
