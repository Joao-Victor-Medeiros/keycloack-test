# Configuração do Keycloak

Após subir os containers da aplicação, acesse o painel administrativo do Keycloak e realize as configurações abaixo.

## 1. Criar o Realm

Acesse o Keycloak:

```text
http://localhost:8080
```

Entre no painel administrativo e crie um novo Realm:

```text
Create Realm
```

Exemplo:

```text
meu-realm
```

Evite utilizar o Realm `master` para os usuários da aplicação.

---

## 2. Criar o Client

Dentro do Realm criado:

```text
Clients
→ Create client
```

Configure:

```text
Client type: OpenID Connect
Client ID: api-domain-dev
```

Na próxima etapa, habilite:

```text
Client authentication: ON
Service accounts roles: ON
```

Salve o client.

---

## 3. Obter o Client Secret

Acesse:

```text
Clients
→ backend-api
→ Credentials
```

Copie o valor de:

```text
Client Secret
```

Esse valor será utilizado pela API através da variável:

```text
KEYCLOAK_CLIENT_SECRET
```

Não versione o secret diretamente no repositório.

Crie um arquivo chamado `.env` na raiz do projeto e adicione a seguinte variável:

```dotenv
KEYCLOAK_CLIENT_SECRET="seu-client-secret"
````
---

## 4. Configurar permissões da API

Acesse:

```text
Clients
→ backend-api
→ Service account roles
→ Assign role
```

Altere o filtro para:

```text
Filter by clients
```

Selecione o client:

```text
realm-management
```

Adicione as roles:

```text
manage-users
view-users
query-users
```

Essas permissões permitem que a API crie, consulte e gerencie usuários no Keycloak.

Não é necessário utilizar:

```text
realm-admin
```

---

## 5. Criar as Roles da aplicação

Acesse:

```text
Realm Roles
→ Create role
```

Crie as seguintes roles:

```text
PACIENTE
PROFISSIONAL_SAUDE
PESQUISADOR
DIRETOR
CADASTRO_PENDENTE
```

A role `CADASTRO_PENDENTE` será utilizada para usuários que precisam passar por validação antes de receber sua role definitiva.

---

## 6. Testar o Client

Para validar a configuração, solicite um token:

```bash
curl -X POST \
  http://localhost:8080/realms/keycloack-test/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id= api-domain-dev" \
  -d "client_secret=0oGmPhDzYhOR1xunp5w7THUjLqVqq261" \
  -d "grant_type=client_credentials"
```

Se a configuração estiver correta, o Keycloak retornará um:

```json
{
  "access_token": "..."
}
```

Com isso, o Keycloak estará configurado para permitir que a API crie e gerencie usuários.
