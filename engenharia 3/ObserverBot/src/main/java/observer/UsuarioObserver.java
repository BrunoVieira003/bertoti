package observer;

import bot.BotTelegram;


public class UsuarioObserver implements Observer {
    private String chatId;
    private BotTelegram bot;

    public UsuarioObserver(String chatId, BotTelegram bot) {
        this.chatId = chatId;
        this.bot = bot;
    }

    @Override
    public void atualizar(String mensagem) {
        bot.enviarMensagem(chatId, "Nova notificação: " + mensagem);
    }

    @Override
    public void atualizarComImagem(byte[] imagem) {
        bot.enviarImagem(chatId, imagem);
    }
}
