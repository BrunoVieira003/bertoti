package bot;

import image.ImageGenerator;
import observer.Observer;
import observer.Subject;
import observer.UsuarioObserver;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

public class BotTelegram extends TelegramLongPollingBot implements Subject {
    private HashMap<String, Observer> observadores = new HashMap<String, Observer>();
    private ImageGenerator imageGenerator = new ImageGenerator();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String username = update.getMessage().getFrom().getFirstName();
            String mensagem = update.getMessage().getText();

            if (mensagem.equalsIgnoreCase("/start")) {
                UsuarioObserver novoUsuario = new UsuarioObserver(chatId, this);
                adicionarObservador(chatId, novoUsuario);
                enviarMensagem(chatId, "Você foi registrado para notificações!");
            }

            if (mensagem.startsWith("/perturbar ")) {
                String notificacao = mensagem.replace("/perturbar ", "");
                try {
                    byte[] imagem = ImageGenerator.createImageWithText(notificacao);
                    notificarObservadoresComImagem(imagem);
                } catch (IOException e) {
                    notificacao = "Não foi possível gerar uma imagem";
                    enviarMensagem(chatId, notificacao);
                }
            }

            if(mensagem.startsWith("/desinscrever")){
                removerObservador(chatId);
                String notificacao = "Você foi removido das notificações";
                enviarMensagem(chatId, notificacao);
            }
        }
    }

    @Override
    public void adicionarObservador(String key, Observer observer) {
        observadores.put(key, observer);
    }

    @Override
    public void removerObservador(String key) {
        observadores.remove(key);
    }

    @Override
    public void notificarObservadores(String mensagem) {
        for (Observer observer : observadores.values()) {
            observer.atualizar(mensagem);
        }
    }

    @Override
    public void notificarObservadoresComImagem(byte[] imagem){
        for (Observer observer : observadores.values()) {
            observer.atualizarComImagem(imagem);
        }
    }

    public void enviarMensagem(String chatId, String mensagem) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(mensagem);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void enviarImagem(String chatId, byte[] imagem){
        InputFile inputFile = new InputFile(new ByteArrayInputStream(imagem), "imagem.png");
        SendPhoto photo = new SendPhoto(chatId, inputFile);
        try {
            execute(photo);
        } catch (TelegramApiException e) {
            System.out.println("Falha ao enviar imagem");
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "SeuBotUsername";
    }

    @Override
    public String getBotToken() {
        return "7534533872:AAEttDOPbAT2TjYvh6m6IAehX2JGuJ7AgWo";
    }
}
