# Santo André On Bus

Aplicativo para Android para consulta de informações de linhas de ônibus da cidade de Santo André.

## Disclaimer
Este aplicativo não é oficial e não possui nenhuma ligação com empresas ou órgãos oficiais de transportes.
Foi desenvolvido unicamente para fins de aprendizagem e não pode ser distribuído comercialmente.

## Ferramentas utilizadas
- Java 7
- Android SDK: Min v21, Target v28
- OKHttp 4.2
- GSON 2.8.5

## Componentes
Localizados em `app/java/br.com.vidal.santoandreonbus` e divididos da seguinte forma:
- `/`: Activities e Fragments para exibição das telas;
- `dialogs`: Mensagens reutilizáveis de progresso de comunicação;
- `models`: Domínio da aplicação. Classes POJO que são hidratadas com dados vindos da API;
- `rest`: Interação com a API REST. Contém clientes HTTPS e placeholders para credenciais;
- `tasks`: Encapsulamento assíncrono para as operações REST para evitar bloqueio da interface gráfica;
- `utilities`: Elementos reaproveitáveis pelas outras partes da aplicação.

## Ambientes
A aplicação está preparada para interagir com APIs REST locais e remotas por HTTPS. Devem ser verificadas as seguintes classes:
- `rest/Env`: Chaveia entre os ambientes de produção (`RestEnvironment.PRODUCTION`) e desenvolvimento (`RestEnvironment.PRODUCTION`) no atributo estático `SELECTED`;
- `rest/EnvForDevelopment` e `rest/EnvForProduction`: Obrigatório preenchimento dos atributos estáticos `USERNAME` e `PASSWORD`;
O ambiente de desenvolvimento está preparado para lidar com certificados digitais auto-assinados, enquanto que o ambiente de produção exige um certificado válido globalmente.

## Comunicação com a API REST
O aplicativo não lida com dados locais, apenas com dados provenientes da API REST (em formato JSON) para garantir que estarão sempre atualizados. O fluxo ocorre da seguinte forma:
1. A `Activity` em execução inicia uma `Task` assíncrona para iniciar o carregamento dos dados sem bloquear a thread da interface gráfica;
2. A `Task` exibe uma mensagem de carregamento e dispara um `Service`. Ocorrendo algum erro, retornará `null` que disparará uma mensagem de erro na `Activity`;
3. O `Service` comporta e padroniza as operações da API REST. Gerencia também o token JWT exigido pela API. Dispara o `RestClient` e retorna dados formatados com nos `models`;
4. O `RestClient` realiza a transmissão HTTPS para o servidor, retornando dados serializados para o `Service`;
5. Objetos nos formatos descritos nos `models` alimentarão controles e adapters presentes nas `Activities` e `Fragments`.

## Recursos textuais e imagens
Todas as frases fixas do aplicativo estão apartadas em `app/res/values`, permitindo fácil alteração sem necessidade de alterar Activities ou Fragments. O mesmo se aplica às dimensões e cores empregadas.<br>
As imagens utilizadas estão presentes em `app/res/drawable`.

## Execução
Este aplicativo não está publicado na App Store. Para executá-lo, inicie a execução a partir do Android Studio.

## Arquitetura da solução
![Principal](https://raw.githubusercontent.com/marcomvidal/SantoAndreOnBus/master/screenshot_todas.png)

## Screenshots
* [Ver](https://raw.githubusercontent.com/marcomvidal/SantoAndreOnBus/master/screenshot_todas.png)