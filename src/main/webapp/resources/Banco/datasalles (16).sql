-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 29-Dez-2019 às 16:16
-- Versão do servidor: 10.4.10-MariaDB
-- versão do PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `datasalles`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `abertura`
--

CREATE TABLE `abertura` (
  `codigo` bigint(20) NOT NULL,
  `dataAbertura` date NOT NULL,
  `valorAbertura` decimal(7,2) NOT NULL,
  `funcionario_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `abertura`
--

INSERT INTO `abertura` (`codigo`, `dataAbertura`, `valorAbertura`, `funcionario_codigo`) VALUES
(1, '2019-12-27', '125.00', 1),
(4, '2019-12-28', '125.00', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `avaria`
--

CREATE TABLE `avaria` (
  `codigo` bigint(20) NOT NULL,
  `horario` datetime NOT NULL,
  `precoTotal` decimal(7,2) NOT NULL,
  `fornecedor_codigo` bigint(20) NOT NULL,
  `funcionario_codigo` bigint(20) NOT NULL,
  `tipoavaria_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `avaria`
--

INSERT INTO `avaria` (`codigo`, `horario`, `precoTotal`, `fornecedor_codigo`, `funcionario_codigo`, `tipoavaria_codigo`) VALUES
(1, '2019-12-24 22:45:29', '252.90', 2, 1, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `caixa`
--

CREATE TABLE `caixa` (
  `codigo` bigint(20) NOT NULL,
  `dataAbertura` date NOT NULL,
  `dataFechamento` date DEFAULT NULL,
  `valorAbertura` decimal(7,2) NOT NULL,
  `funcionario_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cidade`
--

CREATE TABLE `cidade` (
  `codigo` bigint(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `estado_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `cidade`
--

INSERT INTO `cidade` (`codigo`, `nome`, `estado_codigo`) VALUES
(1, 'RECIFE', 1),
(2, 'RIO BRANCO', 2),
(3, 'MACEIO', 3),
(4, 'MACAPA', 4),
(5, 'MANAUS', 5),
(6, 'SALVADOR', 6),
(7, 'FORTALEZA', 7),
(8, 'BRASILIA', 8),
(9, 'VITORIA', 9),
(10, 'GOIANIA', 10),
(11, 'SAO LUIS', 11),
(12, 'CUIABA', 12),
(13, 'CAMPO GRANDE', 13),
(14, 'BELO HORIZONTE', 14),
(15, 'BELEM', 15),
(16, 'JOAO PESSOA', 16),
(17, 'CURITIBA', 17),
(18, 'TERESINA', 18),
(19, 'RIO DE JANEIRO', 19),
(20, 'NATAL', 20),
(21, 'PORTO ALEGRE', 21),
(22, 'PORTO VELHO', 22),
(23, 'BOA VISTA', 23),
(24, 'FLORIANOPOLIS', 24),
(25, 'SAO PAULO', 25),
(26, 'ARACAJU', 26),
(27, 'PALMAS', 27);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `codigo` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `liberado` bit(1) NOT NULL,
  `pessoa_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`codigo`, `dataCadastro`, `liberado`, `pessoa_codigo`) VALUES
(2, '2019-08-24', b'1', 6),
(3, '2019-05-28', b'1', 7),
(4, '2019-06-18', b'1', 10),
(5, '2019-05-18', b'1', 8),
(6, '2019-05-29', b'1', 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `compra`
--

CREATE TABLE `compra` (
  `codigo` bigint(20) NOT NULL,
  `atual` date NOT NULL,
  `precoTotal` decimal(7,2) NOT NULL,
  `vencimento` date NOT NULL,
  `fornecedor_codigo` bigint(20) DEFAULT NULL,
  `funcionario_codigo` bigint(20) NOT NULL,
  `tipopagc_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `compra`
--

INSERT INTO `compra` (`codigo`, `atual`, `precoTotal`, `vencimento`, `fornecedor_codigo`, `funcionario_codigo`, `tipopagc_codigo`) VALUES
(1, '2019-12-24', '10191.00', '2020-01-24', 1, 1, 2),
(2, '2019-12-24', '6590.00', '2020-01-24', 9, 1, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cpagar`
--

CREATE TABLE `cpagar` (
  `codigo` bigint(20) NOT NULL,
  `atual` date NOT NULL,
  `precoTotal` decimal(6,2) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `vencimento` date NOT NULL,
  `fornecedor_codigo` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `cpagar`
--

INSERT INTO `cpagar` (`codigo`, `atual`, `precoTotal`, `tipo`, `vencimento`, `fornecedor_codigo`) VALUES
(1, '2019-12-24', '6590.00', 'Boleto Bancario', '2020-01-24', 9);

-- --------------------------------------------------------

--
-- Estrutura da tabela `creceber`
--

CREATE TABLE `creceber` (
  `codigo` bigint(20) NOT NULL,
  `horario` date NOT NULL,
  `precoTotal` decimal(6,2) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `vencimento` date NOT NULL,
  `cliente_codigo` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `creceber`
--

INSERT INTO `creceber` (`codigo`, `horario`, `precoTotal`, `tipo`, `vencimento`, `cliente_codigo`) VALUES
(1, '2019-12-24', '940.60', 'Boleto Bancario', '2020-01-30', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `estado`
--

CREATE TABLE `estado` (
  `codigo` bigint(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `sigla` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `estado`
--

INSERT INTO `estado` (`codigo`, `nome`, `sigla`) VALUES
(1, 'PERNAMBUCO', 'PE'),
(2, 'ACRE', 'AC'),
(3, 'ALAGOAS', 'AL'),
(4, 'AMAPA', 'AP'),
(5, 'AMAZONAS', 'AM'),
(6, 'BAHIA', 'BA'),
(7, 'CEARA', 'CE'),
(8, 'DISTRITO FEDERAL', 'DF'),
(9, 'ESPIRITO SANTO', 'ES'),
(10, 'GOIAS', 'GO'),
(11, 'MARANHAO', 'MA'),
(12, 'MATO GROSSO', 'MT'),
(13, 'MATO GROSSO DO SUL', 'MS'),
(14, 'MINAS GERAIS', 'MG'),
(15, 'PARA', 'PA'),
(16, 'PARAIBA', 'PB'),
(17, 'PARANA', 'PR'),
(18, 'PIAUI', 'PI'),
(19, 'RIO DE JANEIRO', 'RJ'),
(20, 'RIO GRANDE DO NORTE', 'RN'),
(21, 'RIO GRANDE DO SUL', 'RS'),
(22, 'RONDONIA', 'RO'),
(23, 'RORAIMA', 'RR'),
(24, 'SANTA CATARINA', 'SC'),
(25, 'SAO PAULO', 'SP'),
(26, 'SERGIPE', 'SE'),
(27, 'TOCANTINS', 'TO');

-- --------------------------------------------------------

--
-- Estrutura da tabela `fechamento`
--

CREATE TABLE `fechamento` (
  `codigo` bigint(20) NOT NULL,
  `dataFechamento` date NOT NULL,
  `valorFechamento` decimal(7,2) NOT NULL,
  `funcionario_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE `fornecedor` (
  `codigo` bigint(20) NOT NULL,
  `cnpj` varchar(18) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `fone` varchar(50) NOT NULL,
  `ie` varchar(50) NOT NULL,
  `nfantasia` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`codigo`, `cnpj`, `descricao`, `email`, `fone`, `ie`, `nfantasia`) VALUES
(1, '07.206.816/0052-65', 'M. DIAS BRANCO LTDA', 'sac@vitarella.com.br', '(81)98526-4712', '0541444-00', 'VITARELLA '),
(2, '15.250.084/0001-99', 'LETICIA INFORMATICA ME', 'sac@leticiainformaticame.com.br', '(81) 98463-5396', '0408830-19', 'LETICIA INFORMATICA'),
(3, '75.009.590/0001-12', ' AURORA MARKETING LTDA', 'sac@auroramarketing.com', '(81)99880-4479', '3566859-85', 'AURORA MARKETING'),
(4, '56.030.849/0001-62', 'TIAGO ELETRONICA LTDA', 'contato@tiagoeletronica.com.br', '(87) 98739-6819', '1189586-15', 'TIAGO ELETRONICA'),
(5, '27.570.628/0001-89', 'MARCOS LIMPEZA S/A', 'contato@marcoslimpeza.com', '(81) 99451-7995', '1922238-60', 'MARCOS LIMPEZA'),
(6, '51.036.984/0001-00', 'SABRINA CONTRUCOES LTDA', 'contato@sabinaconstrucoes.com', '(81) 98583-8793', '1464568-82', 'SABRINA CONSTRUCOES'),
(7, '87.253.396/0001-67', 'HEITOR TRANSPORTE  S/A', 'sac@heitortrans.com', '(81) 99674-3040', '0183617-06', 'EITOR TRANSPORTES'),
(8, '60.873.093/0001-90', 'PADARIA DANILO ME', 'contato@padariadanilo.com', '(81) 98819-9535', '6099088-04', 'PADARIA DANILO'),
(9, '97.083.875/0001-36', 'SARA FILMAGENS S/A', 'contato@sarafilmagens.com.br', '(81) 99409-3837', '3254436-70', 'SARA FILMAGENS'),
(10, '23.796.226/0001-46', 'ISABELA GRAFICA LTDA', 'sac@isabelagrafica.com.br', '(81) 99263-6360', '7931798-74', 'ISABELA GRAFICA');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `codigo` bigint(20) NOT NULL,
  `carteiraTrabalho` varchar(15) NOT NULL,
  `dataAdmissao` date NOT NULL,
  `pessoa_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`codigo`, `carteiraTrabalho`, `dataAdmissao`, `pessoa_codigo`) VALUES
(1, '0078321/00022', '2018-01-10', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `historico`
--

CREATE TABLE `historico` (
  `codigo` bigint(20) NOT NULL,
  `horario` date NOT NULL,
  `observacoes` varchar(255) DEFAULT NULL,
  `produto_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `itemavaria`
--

CREATE TABLE `itemavaria` (
  `codigo` bigint(20) NOT NULL,
  `precoParcial` decimal(7,2) NOT NULL,
  `quantidade` smallint(6) NOT NULL,
  `avaria_codigo` bigint(20) NOT NULL,
  `produto_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `itemavaria`
--

INSERT INTO `itemavaria` (`codigo`, `precoParcial`, `quantidade`, `avaria_codigo`, `produto_codigo`) VALUES
(1, '147.40', 5, 1, 19),
(2, '105.50', 5, 1, 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `itemcompra`
--

CREATE TABLE `itemcompra` (
  `codigo` bigint(20) NOT NULL,
  `precoParcial` decimal(7,2) NOT NULL,
  `quantidade` smallint(6) NOT NULL,
  `compra_codigo` bigint(20) NOT NULL,
  `produto_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `itemcompra`
--

INSERT INTO `itemcompra` (`codigo`, `precoParcial`, `quantidade`, `compra_codigo`, `produto_codigo`) VALUES
(1, '2948.00', 100, 1, 19),
(2, '2110.00', 100, 1, 4),
(3, '3309.00', 100, 1, 12),
(4, '1824.00', 100, 1, 8),
(5, '1009.00', 100, 2, 16),
(6, '1756.00', 100, 2, 17),
(7, '3825.00', 100, 2, 18);

-- --------------------------------------------------------

--
-- Estrutura da tabela `itemorca`
--

CREATE TABLE `itemorca` (
  `codigo` bigint(20) NOT NULL,
  `precoParcial` decimal(7,2) NOT NULL,
  `quantidade` smallint(6) NOT NULL,
  `orcamento_codigo` bigint(20) NOT NULL,
  `produto_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `itemorca`
--

INSERT INTO `itemorca` (`codigo`, `precoParcial`, `quantidade`, `orcamento_codigo`, `produto_codigo`) VALUES
(1, '1244.50', 50, 1, 7),
(2, '551.00', 50, 1, 11),
(3, '5960.00', 50, 1, 14);

-- --------------------------------------------------------

--
-- Estrutura da tabela `itemorcac`
--

CREATE TABLE `itemorcac` (
  `codigo` bigint(20) NOT NULL,
  `precoParcial` decimal(7,2) NOT NULL,
  `quantidade` smallint(6) NOT NULL,
  `orcamentoc_codigo` bigint(20) NOT NULL,
  `produto_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `itemorcac`
--

INSERT INTO `itemorcac` (`codigo`, `precoParcial`, `quantidade`, `orcamentoc_codigo`, `produto_codigo`) VALUES
(1, '211.00', 10, 1, 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `itemvenda`
--

CREATE TABLE `itemvenda` (
  `codigo` bigint(20) NOT NULL,
  `precoParcial` decimal(7,2) NOT NULL,
  `quantidade` smallint(6) NOT NULL,
  `produto_codigo` bigint(20) NOT NULL,
  `venda_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `itemvenda`
--

INSERT INTO `itemvenda` (`codigo`, `precoParcial`, `quantidade`, `produto_codigo`, `venda_codigo`) VALUES
(1, '147.40', 5, 19, 1),
(2, '105.50', 5, 4, 1),
(3, '182.40', 10, 8, 1),
(4, '330.90', 10, 12, 2),
(5, '201.80', 20, 16, 2),
(6, '175.60', 10, 17, 3),
(7, '765.00', 20, 18, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `movimentacao`
--

CREATE TABLE `movimentacao` (
  `codigo` bigint(20) NOT NULL,
  `horario` date NOT NULL,
  `valor` decimal(7,2) NOT NULL,
  `abertura_codigo` bigint(20) NOT NULL,
  `funcionario_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `orcamento`
--

CREATE TABLE `orcamento` (
  `codigo` bigint(20) NOT NULL,
  `horario` datetime NOT NULL,
  `precoTotal` decimal(7,2) NOT NULL,
  `cliente_codigo` bigint(20) DEFAULT NULL,
  `funcionario_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `orcamento`
--

INSERT INTO `orcamento` (`codigo`, `horario`, `precoTotal`, `cliente_codigo`, `funcionario_codigo`) VALUES
(1, '2019-12-26 23:36:28', '7755.50', 2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `orcamentoc`
--

CREATE TABLE `orcamentoc` (
  `codigo` bigint(20) NOT NULL,
  `horario` datetime NOT NULL,
  `precoTotal` decimal(7,2) NOT NULL,
  `fornecedor_codigo` bigint(20) DEFAULT NULL,
  `funcionario_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `orcamentoc`
--

INSERT INTO `orcamentoc` (`codigo`, `horario`, `precoTotal`, `fornecedor_codigo`, `funcionario_codigo`) VALUES
(1, '2019-12-29 11:58:53', '211.00', 10, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa`
--

CREATE TABLE `pessoa` (
  `codigo` bigint(20) NOT NULL,
  `bairro` varchar(30) NOT NULL,
  `celular` varchar(14) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `complemento` varchar(10) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `numero` smallint(6) NOT NULL,
  `rg` varchar(12) NOT NULL,
  `rua` varchar(100) NOT NULL,
  `telefone` varchar(13) NOT NULL,
  `cidade_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `pessoa`
--

INSERT INTO `pessoa` (`codigo`, `bairro`, `celular`, `cep`, `complemento`, `cpf`, `email`, `nome`, `numero`, `rg`, `rua`, `telefone`, `cidade_codigo`) VALUES
(1, 'MADALENA', '(81)98829-5777', '50.610-400', '', '453.346.414-91', 'marcoscesarfs@gmail.com', 'MARCOS CESAR FERREIRA DA SILVA', 429, '00.295.752-1', 'RUA CAMPOS SALES', '(81)3034-2894', 1),
(2, 'RECIFE ANTIGO', '(81)99111-1111', '50.500-200', '', '111.111.111-11', 'administrador@gmail.com', 'ADMINISTRADOR', 23, '00.111.111-1', 'RUA JOSE BONIFACIO', '(81)1111-1111', 1),
(3, 'CAXANGA', '(81)92865-4555', '51.310-540', '', '104.866.840-16', 'julietaribeiro@gmail.com', 'JULIETA RIBEIRO COSTA', 56, '33.451.081-8', 'RUA A ', '(81)3182-6996', 1),
(4, 'MUSTARDINHA', '(81)95921-6088', '52.280-245', 'APT 03', '202.794.110-06', 'giovannacastro@gmail.com', 'GIOVANNA CASTRO CARVALHO', 78, '50.003.157-5', 'RUA PATRIA AMADA', '(81)3042-5479', 1),
(5, 'PEIXINHOS', '(81)96066-9154', '52.120-130', 'APT 203', '682.963.500-41', 'fernandaferreira@gmail.com', 'FERNANDA FERREIRA PINTO', 125, '13.512.207-7', 'RUA DO CAJU', '(81)3094-7285', 1),
(6, 'PRAZERES', '(81)90397-9985', '52.040-091', 'LJ 04', '310.734.390-03', 'gabrielaoliveira@gmail.com', 'GABRIELA OLIVEIRA DIAS', 487, '35.894.147-7', 'RUA IBIA', '(81)3468-4771', 1),
(7, 'CORDEIRO', '(81)99866-3019', '53.420-610', 'APT 108', '314.673.710-84', 'juliaribeiro@gmail.com', 'JULIA RIBEIRO CARVALHO', 1239, '13.320.087-5', 'RUA VINICIUS DE MORAES', '(81)3466-4571', 1),
(8, 'JORDAO', '(81)93320-9600', '50.660-400', 'LJ 01', '888.131.140-28', 'arturrodrigues@gmail.com', 'ARTUR RODRIGUES CORREIA', 124, '48.555.044-1', 'RUA FLORESTA FERNANDES', '(81)679-1586', 1),
(9, 'VILA RICA', '(81)91540-2401', '52.090-168', 'APT 1003', '426.065.130-70', 'vitorsouza@gmail.com', 'VITOR SOUZA FERNANDES', 702, '25.759.232-5', 'AV. EUGENIO MARQUES', '(81)3904-5900', 1),
(10, 'IGARASSU', '(81)92994-5341', '51.120-130', 'CASA 5', '910.756.380-97', 'vitoriasouza@gmail.com', 'VITORIA SOUZA PEREIRA', 654, '47.940.512-8', 'QUADRA SIA -C', '(81)3357-7721', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `codigo` bigint(20) NOT NULL,
  `descricao` varchar(80) NOT NULL,
  `preco` decimal(6,2) NOT NULL,
  `quantidade` smallint(6) NOT NULL,
  `fornecedor_codigo` bigint(20) NOT NULL,
  `marckup` smallint(6) NOT NULL,
  `codbarras` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`codigo`, `descricao`, `preco`, `quantidade`, `fornecedor_codigo`, `marckup`, `codbarras`) VALUES
(1, 'MACARRAO VITARELLA 500G', '18.90', 0, 1, 5, '7894566782123'),
(2, 'FARINHA DE TRIGO ROSA BRANCA 500G', '22.30', 0, 2, 5, '7891980227425'),
(3, 'FARINHA DE MANDIOCA ROCA 500G', '14.70', 0, 3, 2, '7892879318347'),
(4, 'ACUCAR CRISTAL OLHO DAGUA 500G', '21.10', 90, 4, 3, '7893761409259'),
(5, 'MARGARINA DELINE 250G', '32.40', 0, 5, 6, '7894652590162'),
(6, 'GUARANA ANTARTICA 2 LT', '19.80', 0, 6, 7, '7895543681075'),
(7, 'SABAO EM PO BEM-TE-VI 500G', '24.89', 0, 7, 4, '7896434772988'),
(8, 'BISCOITO TRELOSO CHOCOLATE 130G', '18.24', 90, 8, 2, '7897325863891'),
(9, 'FEIJAO CARIOCA AZEDINHO 1KG', '16.78', 0, 9, 5, '7898216954704'),
(10, 'CHARQUE PONTA DE AGULHA 1KG', '28.42', 0, 10, 5, '7899107045617'),
(11, 'SUCO DE LARANJA AMARELINHO 1LT', '11.02', 0, 1, 9, '7891908227421'),
(12, 'AZEITE DE OLIVA GALLO 500ML', '33.09', 90, 2, 8, '7892817318347'),
(13, 'CARNE MOIDA KG', '15.66', 0, 3, 2, '7898219547071'),
(14, 'WHISKY JOHNNIE WALKER BLACK 1 LT ', '119.20', 0, 4, 6, '7896213002657'),
(15, 'FLOCAO DE MILHO CUSCUMIL 500G', '56.70', 0, 5, 7, '7899100456182'),
(16, 'CAFE PILAR EXTRA FORTE 500G', '10.09', 80, 6, 5, '7894655390165'),
(17, 'PRESUNTO DE PEITO DE PERU 1KG', '17.56', 90, 7, 4, '7895546841070'),
(18, 'QUEIJO MUSSARELA 1KG', '38.25', 80, 8, 6, '7896437725981'),
(19, 'ACHOCOLATADO NESCAU 1LT', '29.48', 90, 9, 2, '7897328638694'),
(20, 'LEITE EM PO NINHO 3KG', '35.64', 0, 10, 3, '7893762409258');

-- --------------------------------------------------------

--
-- Estrutura da tabela `sangria`
--

CREATE TABLE `sangria` (
  `codigo` bigint(20) NOT NULL,
  `sangria` decimal(19,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipoavaria`
--

CREATE TABLE `tipoavaria` (
  `codigo` bigint(20) NOT NULL,
  `descricao` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tipoavaria`
--

INSERT INTO `tipoavaria` (`codigo`, `descricao`) VALUES
(1, 'Produto Improprio para Consumo'),
(2, 'Produto Fora de Validade'),
(3, 'Produto Quebrado'),
(4, 'Produto Molhado');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipopag`
--

CREATE TABLE `tipopag` (
  `codigo` bigint(20) NOT NULL,
  `descricao` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tipopag`
--

INSERT INTO `tipopag` (`codigo`, `descricao`) VALUES
(1, 'A Vista'),
(2, 'Boleto Bancario');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipopagc`
--

CREATE TABLE `tipopagc` (
  `codigo` bigint(20) NOT NULL,
  `descricao` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tipopagc`
--

INSERT INTO `tipopagc` (`codigo`, `descricao`) VALUES
(1, 'A Vista'),
(2, 'Boleto Bancario');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `codigo` bigint(20) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `senha` varchar(32) NOT NULL,
  `tipo` char(1) NOT NULL,
  `pessoa_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`codigo`, `ativo`, `senha`, `tipo`, `pessoa_codigo`) VALUES
(1, b'1', '202cb962ac59075b964b07152d234b70', 'A', 1),
(2, b'1', '202cb962ac59075b964b07152d234b70', 'A', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE `venda` (
  `codigo` bigint(20) NOT NULL,
  `horario` date NOT NULL,
  `precoTotal` decimal(7,2) NOT NULL,
  `vencimento` date NOT NULL,
  `cliente_codigo` bigint(20) DEFAULT NULL,
  `funcionario_codigo` bigint(20) NOT NULL,
  `tipopag_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `venda`
--

INSERT INTO `venda` (`codigo`, `horario`, `precoTotal`, `vencimento`, `cliente_codigo`, `funcionario_codigo`, `tipopag_codigo`) VALUES
(1, '2019-12-24', '435.30', '2019-12-26', 4, 1, 1),
(2, '2019-12-24', '532.70', '2019-12-26', 3, 1, 1),
(3, '2019-12-24', '940.60', '2020-01-30', 5, 1, 2);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `abertura`
--
ALTER TABLE `abertura`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `dataAbertura` (`dataAbertura`),
  ADD KEY `FK_573kskix9gpqu9cnby5wnpvkg` (`funcionario_codigo`);

--
-- Índices para tabela `avaria`
--
ALTER TABLE `avaria`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_kqouiqrgtuvaq71lds025r06c` (`fornecedor_codigo`),
  ADD KEY `FK_kqc7avk7lw0y8vwgw3j4ntori` (`funcionario_codigo`),
  ADD KEY `FK_5x2bfqdjnphdb96bac3pm0wtp` (`tipoavaria_codigo`);

--
-- Índices para tabela `caixa`
--
ALTER TABLE `caixa`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_21rau1s11pi6oiw9o1t6famnu` (`funcionario_codigo`);

--
-- Índices para tabela `cidade`
--
ALTER TABLE `cidade`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_3uxfxu3ar4lpub19hldftpikb` (`estado_codigo`);

--
-- Índices para tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_mobustx1b8qkdhosqdhnog3lq` (`pessoa_codigo`);

--
-- Índices para tabela `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_9mrwikdu6lnusfnwau18vgeqe` (`fornecedor_codigo`),
  ADD KEY `FK_q521nd8whh19ia70o8retc618` (`funcionario_codigo`),
  ADD KEY `FK_bqqypjonh7lv6uv9rmfhf11fw` (`tipopagc_codigo`);

--
-- Índices para tabela `cpagar`
--
ALTER TABLE `cpagar`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_fh4hjt9e4q64ba8b2srwg8mor` (`fornecedor_codigo`);

--
-- Índices para tabela `creceber`
--
ALTER TABLE `creceber`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_sel8oe8mkvtcpysrakatt4j5r` (`cliente_codigo`);

--
-- Índices para tabela `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices para tabela `fechamento`
--
ALTER TABLE `fechamento`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_3g4c49wsbk6nb7ce0byd9qafr` (`funcionario_codigo`);

--
-- Índices para tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `UK_p4ry2b3ow2yqqg3sin78ab8uf` (`cnpj`);

--
-- Índices para tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_l9i1mm6ohy2192qfudb90xhc6` (`pessoa_codigo`);

--
-- Índices para tabela `historico`
--
ALTER TABLE `historico`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_mqu402bbqhf0pqy2n6nfxc31n` (`produto_codigo`);

--
-- Índices para tabela `itemavaria`
--
ALTER TABLE `itemavaria`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_qt1o74qlrkld56v3c7p4aqgq5` (`avaria_codigo`),
  ADD KEY `FK_pqtwogkoardp0h47cwgxhtl7h` (`produto_codigo`);

--
-- Índices para tabela `itemcompra`
--
ALTER TABLE `itemcompra`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_lfn94jt5tg9i8m6cbu1tmxh7i` (`compra_codigo`),
  ADD KEY `FK_fo00f20srni8tww4kuvkmdo8p` (`produto_codigo`);

--
-- Índices para tabela `itemorca`
--
ALTER TABLE `itemorca`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_qa5oavvwy5hvd8h2mjrngvr07` (`orcamento_codigo`),
  ADD KEY `FK_4di694u3bguu1o2nw0vuvi3b9` (`produto_codigo`);

--
-- Índices para tabela `itemorcac`
--
ALTER TABLE `itemorcac`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_8xtaxl8d9htbbgpuwvyq10ugh` (`orcamentoc_codigo`),
  ADD KEY `FK_53y2vq1r6fwi78jd3sbi27cqw` (`produto_codigo`);

--
-- Índices para tabela `itemvenda`
--
ALTER TABLE `itemvenda`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_afhuh5uapr0pkljm58r86bnof` (`produto_codigo`),
  ADD KEY `FK_34u5r6crbeyi67pm4ptlha46u` (`venda_codigo`);

--
-- Índices para tabela `movimentacao`
--
ALTER TABLE `movimentacao`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_ml1o9ukxkpn3ukqk01psjirar` (`abertura_codigo`),
  ADD KEY `FK_cg6vmly6dvu78o60rkjidn252` (`funcionario_codigo`);

--
-- Índices para tabela `orcamento`
--
ALTER TABLE `orcamento`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_slv2u118cmbj0tg5422o0q3m1` (`cliente_codigo`),
  ADD KEY `FK_9y1ujpxvo2ratiegaw1q3njlp` (`funcionario_codigo`);

--
-- Índices para tabela `orcamentoc`
--
ALTER TABLE `orcamentoc`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_dcv6r7s1usnrhvpiq086rilwd` (`fornecedor_codigo`),
  ADD KEY `FK_40e71sr5x5wa7efrxd85crwaa` (`funcionario_codigo`);

--
-- Índices para tabela `pessoa`
--
ALTER TABLE `pessoa`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_ru7rwevg7kj864u8vkyo8vbyi` (`cidade_codigo`);

--
-- Índices para tabela `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_js72o9rt9rkmbkgwyc8u6vixg` (`fornecedor_codigo`);

--
-- Índices para tabela `sangria`
--
ALTER TABLE `sangria`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices para tabela `tipoavaria`
--
ALTER TABLE `tipoavaria`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices para tabela `tipopag`
--
ALTER TABLE `tipopag`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices para tabela `tipopagc`
--
ALTER TABLE `tipopagc`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices para tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_s8lrxfgvascltkib6t418n5ef` (`pessoa_codigo`);

--
-- Índices para tabela `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_ass4ciu3lp1ipkofx918j7nm7` (`cliente_codigo`),
  ADD KEY `FK_5twcbl0srrivgeocts0y19trf` (`funcionario_codigo`),
  ADD KEY `FK_tjky9a0yoeelh7aeo7l9ga6pa` (`tipopag_codigo`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `abertura`
--
ALTER TABLE `abertura`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de tabela `avaria`
--
ALTER TABLE `avaria`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `caixa`
--
ALTER TABLE `caixa`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `cidade`
--
ALTER TABLE `cidade`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `compra`
--
ALTER TABLE `compra`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `cpagar`
--
ALTER TABLE `cpagar`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `creceber`
--
ALTER TABLE `creceber`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `estado`
--
ALTER TABLE `estado`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de tabela `fechamento`
--
ALTER TABLE `fechamento`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `historico`
--
ALTER TABLE `historico`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `itemavaria`
--
ALTER TABLE `itemavaria`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `itemcompra`
--
ALTER TABLE `itemcompra`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `itemorca`
--
ALTER TABLE `itemorca`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `itemorcac`
--
ALTER TABLE `itemorcac`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `itemvenda`
--
ALTER TABLE `itemvenda`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `movimentacao`
--
ALTER TABLE `movimentacao`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `orcamento`
--
ALTER TABLE `orcamento`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `orcamentoc`
--
ALTER TABLE `orcamentoc`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de tabela `sangria`
--
ALTER TABLE `sangria`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tipoavaria`
--
ALTER TABLE `tipoavaria`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `tipopag`
--
ALTER TABLE `tipopag`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `tipopagc`
--
ALTER TABLE `tipopagc`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `venda`
--
ALTER TABLE `venda`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `abertura`
--
ALTER TABLE `abertura`
  ADD CONSTRAINT `FK_573kskix9gpqu9cnby5wnpvkg` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`);

--
-- Limitadores para a tabela `avaria`
--
ALTER TABLE `avaria`
  ADD CONSTRAINT `FK_5x2bfqdjnphdb96bac3pm0wtp` FOREIGN KEY (`tipoavaria_codigo`) REFERENCES `tipoavaria` (`codigo`),
  ADD CONSTRAINT `FK_kqc7avk7lw0y8vwgw3j4ntori` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`),
  ADD CONSTRAINT `FK_kqouiqrgtuvaq71lds025r06c` FOREIGN KEY (`fornecedor_codigo`) REFERENCES `fornecedor` (`codigo`);

--
-- Limitadores para a tabela `caixa`
--
ALTER TABLE `caixa`
  ADD CONSTRAINT `FK_21rau1s11pi6oiw9o1t6famnu` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`);

--
-- Limitadores para a tabela `cidade`
--
ALTER TABLE `cidade`
  ADD CONSTRAINT `FK_3uxfxu3ar4lpub19hldftpikb` FOREIGN KEY (`estado_codigo`) REFERENCES `estado` (`codigo`);

--
-- Limitadores para a tabela `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FK_mobustx1b8qkdhosqdhnog3lq` FOREIGN KEY (`pessoa_codigo`) REFERENCES `pessoa` (`codigo`);

--
-- Limitadores para a tabela `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `FK_9mrwikdu6lnusfnwau18vgeqe` FOREIGN KEY (`fornecedor_codigo`) REFERENCES `fornecedor` (`codigo`),
  ADD CONSTRAINT `FK_bqqypjonh7lv6uv9rmfhf11fw` FOREIGN KEY (`tipopagc_codigo`) REFERENCES `tipopagc` (`codigo`),
  ADD CONSTRAINT `FK_q521nd8whh19ia70o8retc618` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`);

--
-- Limitadores para a tabela `cpagar`
--
ALTER TABLE `cpagar`
  ADD CONSTRAINT `FK_fh4hjt9e4q64ba8b2srwg8mor` FOREIGN KEY (`fornecedor_codigo`) REFERENCES `fornecedor` (`codigo`);

--
-- Limitadores para a tabela `creceber`
--
ALTER TABLE `creceber`
  ADD CONSTRAINT `FK_sel8oe8mkvtcpysrakatt4j5r` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`);

--
-- Limitadores para a tabela `fechamento`
--
ALTER TABLE `fechamento`
  ADD CONSTRAINT `FK_3g4c49wsbk6nb7ce0byd9qafr` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`);

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `FK_l9i1mm6ohy2192qfudb90xhc6` FOREIGN KEY (`pessoa_codigo`) REFERENCES `pessoa` (`codigo`);

--
-- Limitadores para a tabela `historico`
--
ALTER TABLE `historico`
  ADD CONSTRAINT `FK_mqu402bbqhf0pqy2n6nfxc31n` FOREIGN KEY (`produto_codigo`) REFERENCES `produto` (`codigo`);

--
-- Limitadores para a tabela `itemavaria`
--
ALTER TABLE `itemavaria`
  ADD CONSTRAINT `FK_pqtwogkoardp0h47cwgxhtl7h` FOREIGN KEY (`produto_codigo`) REFERENCES `produto` (`codigo`),
  ADD CONSTRAINT `FK_qt1o74qlrkld56v3c7p4aqgq5` FOREIGN KEY (`avaria_codigo`) REFERENCES `avaria` (`codigo`);

--
-- Limitadores para a tabela `itemcompra`
--
ALTER TABLE `itemcompra`
  ADD CONSTRAINT `FK_fo00f20srni8tww4kuvkmdo8p` FOREIGN KEY (`produto_codigo`) REFERENCES `produto` (`codigo`),
  ADD CONSTRAINT `FK_lfn94jt5tg9i8m6cbu1tmxh7i` FOREIGN KEY (`compra_codigo`) REFERENCES `compra` (`codigo`);

--
-- Limitadores para a tabela `itemorca`
--
ALTER TABLE `itemorca`
  ADD CONSTRAINT `FK_4di694u3bguu1o2nw0vuvi3b9` FOREIGN KEY (`produto_codigo`) REFERENCES `produto` (`codigo`),
  ADD CONSTRAINT `FK_qa5oavvwy5hvd8h2mjrngvr07` FOREIGN KEY (`orcamento_codigo`) REFERENCES `orcamento` (`codigo`);

--
-- Limitadores para a tabela `itemorcac`
--
ALTER TABLE `itemorcac`
  ADD CONSTRAINT `FK_53y2vq1r6fwi78jd3sbi27cqw` FOREIGN KEY (`produto_codigo`) REFERENCES `produto` (`codigo`),
  ADD CONSTRAINT `FK_8xtaxl8d9htbbgpuwvyq10ugh` FOREIGN KEY (`orcamentoc_codigo`) REFERENCES `orcamentoc` (`codigo`);

--
-- Limitadores para a tabela `itemvenda`
--
ALTER TABLE `itemvenda`
  ADD CONSTRAINT `FK_34u5r6crbeyi67pm4ptlha46u` FOREIGN KEY (`venda_codigo`) REFERENCES `venda` (`codigo`),
  ADD CONSTRAINT `FK_afhuh5uapr0pkljm58r86bnof` FOREIGN KEY (`produto_codigo`) REFERENCES `produto` (`codigo`);

--
-- Limitadores para a tabela `movimentacao`
--
ALTER TABLE `movimentacao`
  ADD CONSTRAINT `FK_cg6vmly6dvu78o60rkjidn252` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`),
  ADD CONSTRAINT `FK_ml1o9ukxkpn3ukqk01psjirar` FOREIGN KEY (`abertura_codigo`) REFERENCES `abertura` (`codigo`);

--
-- Limitadores para a tabela `orcamento`
--
ALTER TABLE `orcamento`
  ADD CONSTRAINT `FK_9y1ujpxvo2ratiegaw1q3njlp` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`),
  ADD CONSTRAINT `FK_slv2u118cmbj0tg5422o0q3m1` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`);

--
-- Limitadores para a tabela `orcamentoc`
--
ALTER TABLE `orcamentoc`
  ADD CONSTRAINT `FK_40e71sr5x5wa7efrxd85crwaa` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`),
  ADD CONSTRAINT `FK_dcv6r7s1usnrhvpiq086rilwd` FOREIGN KEY (`fornecedor_codigo`) REFERENCES `fornecedor` (`codigo`);

--
-- Limitadores para a tabela `pessoa`
--
ALTER TABLE `pessoa`
  ADD CONSTRAINT `FK_ru7rwevg7kj864u8vkyo8vbyi` FOREIGN KEY (`cidade_codigo`) REFERENCES `cidade` (`codigo`);

--
-- Limitadores para a tabela `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `FK_js72o9rt9rkmbkgwyc8u6vixg` FOREIGN KEY (`fornecedor_codigo`) REFERENCES `fornecedor` (`codigo`);

--
-- Limitadores para a tabela `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK_s8lrxfgvascltkib6t418n5ef` FOREIGN KEY (`pessoa_codigo`) REFERENCES `pessoa` (`codigo`);

--
-- Limitadores para a tabela `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `FK_5twcbl0srrivgeocts0y19trf` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`),
  ADD CONSTRAINT `FK_ass4ciu3lp1ipkofx918j7nm7` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`),
  ADD CONSTRAINT `FK_tjky9a0yoeelh7aeo7l9ga6pa` FOREIGN KEY (`tipopag_codigo`) REFERENCES `tipopag` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
