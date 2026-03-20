# OrderHub Mobile

Aplicativo Android para gerenciamento de agendamentos e pedidos, desenvolvido com Kotlin e Jetpack Compose.

## Tecnologias

- **Kotlin** com **Jetpack Compose**
- **Navigation Compose** para navegação entre telas
- **Retrofit** + **OkHttp** para chamadas de API REST
- **Coil** para carregamento de imagens
- **Google Maps** para funcionalidades de mapa
- **Material3** para componentes de UI

## Pré-requisitos

- Android Studio Koala (2024.1.1) ou superior (recomendado: versão mais recente estável)
- JDK 11
- Android SDK compileSdk 35, minSdk 28
- Conta Google com acesso ao Google Maps API (para a funcionalidade de mapa)

## Configuração do projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/Grupo-2-PI-Terceiro-semestre/order-hub-mobile.git
   ```
2. Abra o projeto no Android Studio ou IntelliJ IDEA.
3. Adicione sua chave da API do Google Maps no arquivo `local.properties`:
   ```
   MAPS_API_KEY=sua_chave_aqui
   ```
4. Aguarde a sincronização do Gradle e então execute o app em um emulador ou dispositivo físico.

## Configurando o GitHub Copilot no IntelliJ / Android Studio

Se o IntelliJ ou Android Studio continuar exibindo a mensagem **"É necessário fazer upgrade"** ou **"Upgrade required"** mesmo depois de adquirir o GitHub Copilot, siga os passos abaixo:

### 1. Verifique se o plugin está instalado e atualizado

1. Abra **File → Settings** (Windows/Linux) ou **IntelliJ IDEA → Settings** (macOS).
2. Acesse **Plugins** e procure por **GitHub Copilot**.
3. Caso o plugin não esteja instalado, clique em **Install**.
4. Se já estiver instalado, verifique se há atualização disponível e clique em **Update**.
5. Reinicie o IDE após a instalação/atualização.

### 2. Faça login com a conta correta do GitHub

1. Abra **File → Settings → Tools → GitHub Copilot**.
2. Clique em **Sign out** (caso já esteja autenticado com outra conta).
3. Clique em **Sign in to GitHub** e autentique com a conta que possui a assinatura do Copilot ativa.
4. Autorize o aplicativo no browser quando solicitado.
5. Aguarde a confirmação e retorne ao IDE.

### 3. Confirme a assinatura no GitHub

1. Acesse [github.com/settings/copilot](https://github.com/settings/copilot) no browser.
2. Verifique se a sua assinatura está **ativa** e associada à conta usada no passo anterior.
3. Caso a assinatura tenha sido adquirida recentemente, aguarde alguns minutos para a propagação da licença e tente fazer login novamente.

### 4. Verifique o plano do Copilot

O GitHub Copilot oferece diferentes planos:

| Plano | IDEs suportadas |
|---|---|
| Copilot Free | VS Code, Visual Studio, Neovim e outros (suporte limitado) |
| Copilot Individual / Pro | Todos os IDEs suportados, incluindo IntelliJ / Android Studio |
| Copilot Business / Enterprise | Todos os IDEs suportados |

> **Atenção:** O plano **Copilot Free** possui restrições de uso em alguns IDEs. Para utilizar o Copilot no IntelliJ ou Android Studio sem limitações, é necessário o plano **Copilot Individual**, **Pro**, **Business** ou **Enterprise**.  
> Verifique seu plano em [github.com/settings/copilot](https://github.com/settings/copilot).

### 5. Se o problema persistir

- Limpe o cache do IDE: **File → Invalidate Caches / Restart → Invalidate and Restart**.
- Desinstale e reinstale o plugin GitHub Copilot.
- Verifique se o firewall ou proxy da sua rede não está bloqueando o acesso aos servidores do GitHub Copilot (`*.githubcopilot.com`).

## Contribuindo

1. Crie uma branch a partir de `dev`:
   ```bash
   git checkout -b feature/minha-feature dev
   ```
2. Faça suas alterações e commit:
   ```bash
   git commit -m "feat: descrição da alteração"
   ```
3. Abra um Pull Request direcionado para a branch `dev`.

## Licença

Este projeto é de uso educacional e desenvolvido pelo Grupo 2 do Projeto Integrador - 3º Semestre.
