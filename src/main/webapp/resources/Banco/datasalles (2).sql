-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 06-Dez-2020 às 19:17
-- Versão do servidor: 10.4.17-MariaDB
-- versão do PHP: 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
(4, '2019-12-28', '125.00', 1),
(19, '2019-12-31', '128.00', 1),
(21, '2020-01-23', '210.00', 1),
(22, '2020-01-24', '362.00', 1),
(23, '2020-01-25', '1280.00', 1),
(24, '2020-01-28', '128.00', 1),
(25, '2020-01-29', '133.00', 1),
(26, '2020-01-30', '134.20', 1),
(27, '2020-01-31', '127.00', 1),
(28, '2020-02-01', '127.00', 1),
(29, '2020-02-02', '213.00', 1),
(30, '2020-02-03', '183.50', 1),
(31, '2020-02-15', '128.50', 1),
(32, '2020-03-05', '125.00', 1),
(33, '2020-03-06', '125.30', 1),
(34, '2020-03-07', '25000.30', 1),
(35, '2020-03-12', '580.00', 1),
(36, '2020-03-31', '1275.00', 1),
(37, '2020-10-03', '15.50', 1);

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
(1, '2019-12-24 22:45:29', '252.90', 2, 1, 1),
(2, '2020-01-03 20:46:00', '330.90', 3, 1, 3),
(3, '2020-02-04 23:38:13', '341.00', 3, 1, 3),
(4, '2020-02-04 23:39:12', '100.90', 3, 1, 4);

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
  `tipopagc_codigo` bigint(20) NOT NULL,
  `nfcompra` varchar(255) DEFAULT NULL,
  `pedido` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `compra`
--

INSERT INTO `compra` (`codigo`, `atual`, `precoTotal`, `vencimento`, `fornecedor_codigo`, `funcionario_codigo`, `tipopagc_codigo`, `nfcompra`, `pedido`, `status`) VALUES
(3, '2020-01-04', '359.10', '2020-01-31', 1, 1, 2, '78921', '0', 0),
(4, '2020-01-04', '817.29', '2020-01-29', 3, 1, 2, '0', '0', 0),
(5, '2020-01-19', '182.40', '2020-01-31', 3, 1, 2, '78986', '3', 0),
(6, '2020-01-23', '852.50', '2020-01-23', 9, 1, 1, '45621', '4', 1),
(7, '2020-01-25', '1957.20', '2020-01-25', 5, 1, 1, '32567', '5', 1),
(8, '2020-01-30', '201.80', '2020-01-30', 4, 1, 1, '45689', '5', 1),
(9, '2020-01-31', '533.40', '2020-01-31', 4, 1, 1, '96852', '6', 1),
(10, '2020-02-01', '169.36', '2020-02-01', 4, 1, 1, '65241', '7', 1),
(11, '2020-02-02', '342.86', '2020-02-02', 2, 1, 1, '98256', '8', 1),
(12, '2020-02-15', '1992.60', '2020-02-29', 5, 1, 2, '88547', '', 0),
(13, '2020-02-15', '567.00', '2020-02-17', 5, 1, 1, '44278', '', 1),
(14, '2020-02-15', '1244.50', '2020-02-17', 2, 1, 1, '35241', '', 1),
(15, '2020-02-15', '440.80', '2020-02-17', 10, 1, 1, '63421', '', 1),
(16, '2020-02-15', '4768.00', '2020-02-17', 9, 1, 1, '33452', '', 1),
(17, '2020-03-06', '1481.10', '2020-03-06', 5, 1, 1, '45879', '7', 1),
(18, '2020-03-06', '6343.00', '2020-03-06', 2, 1, 1, '98564', '', 1),
(19, '2020-03-06', '4552.20', '2020-03-06', 4, 1, 1, '65478', '18', 1),
(20, '2020-03-06', '1902.90', '2020-03-06', 1, 1, 1, '98754', '19', 1),
(21, '2020-03-06', '8975.20', '2020-03-06', 3, 1, 1, '34217', '19', 1),
(23, '2020-03-06', '6885.00', '2020-03-06', 9, 1, 1, '35471', '20', 1),
(24, '2020-03-06', '8148.60', '2020-03-06', 4, 1, 1, '95471', '21', 1),
(25, '2020-03-12', '2110.00', '2020-03-12', 3, 1, 1, '78954', '', 1),
(26, '2020-03-12', '9536.00', '2020-03-12', 3, 1, 1, '33564', '', 1),
(27, '2020-03-12', '341.00', '2020-03-12', 2, 1, 1, '58471', '', 1),
(28, '2020-03-12', '1364.00', '2020-03-12', 3, 1, 1, '65894', '', 1),
(29, '2020-03-12', '3576.00', '2020-03-12', 9, 1, 1, '74523', '', 1),
(30, '2020-10-03', '7062.00', '2020-12-03', 8, 1, 2, '7845', '512', 0),
(31, '2020-10-03', '152.00', '2020-10-03', 6, 1, 1, '7845', '', 1);

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
(1, '2019-12-24', '6590.00', 'Boleto Bancario', '2020-01-24', 9),
(4, '2020-01-04', '359.10', 'Boleto Bancario', '2020-01-31', 1),
(6, '2020-01-04', '817.29', 'Boleto Bancario', '2020-01-29', 3),
(7, '2020-01-09', '985.00', 'Entrada Manual', '2020-01-31', 1),
(8, '2020-01-19', '182.40', 'Boleto Bancario', '2020-01-31', 3),
(24, '2020-02-15', '1992.60', 'Boleto Bancario', '2020-02-29', 5),
(25, '2020-02-15', '1992.60', 'Boleto Bancario', '2020-02-29', 5),
(31, '2020-03-06', '1481.10', 'A Vista', '2020-03-06', 5),
(32, '2020-03-06', '1481.10', 'A Vista', '2020-03-06', 5),
(33, '2020-03-06', '1481.10', 'A Vista', '2020-03-06', 5),
(34, '2020-03-06', '1481.10', 'A Vista', '2020-03-06', 5),
(35, '2020-03-06', '1481.10', 'A Vista', '2020-03-06', 5),
(36, '2020-03-06', '6343.00', 'A Vista', '2020-03-06', 2),
(37, '2020-03-06', '6343.00', 'A Vista', '2020-03-06', 2),
(38, '2020-03-06', '6343.00', 'A Vista', '2020-03-06', 2),
(39, '2020-03-06', '4552.20', 'A Vista', '2020-03-06', 4),
(40, '2020-03-06', '4552.20', 'A Vista', '2020-03-06', 4),
(41, '2020-03-06', '1902.90', 'A Vista', '2020-03-06', 1),
(42, '2020-03-06', '1902.90', 'A Vista', '2020-03-06', 1),
(43, '2020-03-06', '1902.90', 'A Vista', '2020-03-06', 1),
(44, '2020-03-06', '8975.20', 'A Vista', '2020-03-06', 3),
(45, '2020-03-06', '8975.20', 'A Vista', '2020-03-06', 3),
(46, '2020-03-06', '8975.20', 'A Vista', '2020-03-06', 3),
(47, '2020-03-06', '8975.20', 'A Vista', '2020-03-06', 3),
(48, '2020-03-06', '8975.20', 'A Vista', '2020-03-06', 3),
(49, '2020-10-03', '7062.00', 'Boleto Bancario', '2020-12-03', 8),
(50, '2020-10-03', '7062.00', 'Boleto Bancario', '2020-12-03', 8);

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
(1, '2019-12-24', '940.60', 'Boleto Bancario', '2020-01-30', 5),
(2, '2020-01-05', '341.00', 'Boleto Bancario', '2020-01-31', 2),
(3, '2020-01-09', '1890.00', 'Entrada Manual', '2020-01-31', 4),
(4, '2020-02-15', '505.80', 'Boleto Bancario', '2020-02-29', 2),
(5, '2020-10-03', '420.40', 'Boleto Bancario', '2020-11-03', 2);

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

--
-- Extraindo dados da tabela `fechamento`
--

INSERT INTO `fechamento` (`codigo`, `dataFechamento`, `valorFechamento`, `funcionario_codigo`) VALUES
(1, '2019-12-31', '126.00', 1);

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
(2, '15.250.084/0001-99', 'A&B ALIMENTOS E BEBIDAS LTDA', 'sac@aebalimentos.com.br', '(81)98463-5396', '0408830-19', 'A&B ALIMENTOS E BEBIDAS'),
(3, '75.009.590/0001-12', 'HB ALIMENTOS LTDA', 'sac@hbalimentos.com.br', '(81)99880-4479', '3566859-85', 'HB ALIMENTOS'),
(4, '56.030.849/0001-62', 'CADAN ALIMENTOS LTDA', 'contato@cadanalimentos.com.br', '(87)98739-6819', '1189586-15', 'CADAN ALIMENTOS LTDA'),
(5, '27.570.628/0001-89', 'JR ALIMENTOS E BEBIDAS S/A', 'contato@jralimentos.com.br', '(81)99451-7995', '1922238-60', 'JR ALIMENTOS E BEBIDAS'),
(6, '51.036.984/0001-00', 'RM ATACADO E VAREJO LTDA', 'contato@rmatacado.com.br', '(81)98583-8793', '1464568-82', 'RM ATACADO E VAREJO'),
(7, '87.253.396/0001-67', 'UNILEVER PRODUTOS  S/A', 'sac@produtosunilever.com', '(81)99674-3040', '0183617-06', 'UNILEVER PRODUTOS'),
(8, '60.873.093/0001-90', 'ATACADO MARTINS ME', 'contato@atacadomartins.com', '(81)98819-9535', '6099088-04', 'ATACADO MARTINS'),
(9, '97.083.875/0001-36', 'ATACAMAX DISTRIBUIDORAS S/A', 'contato@atacamax.com.br', '(81)99409-3837', '3254436-70', 'ATACAMAX DISTRIBUIDORAS'),
(10, '23.796.226/0001-46', 'COMAL - COM. ATACADISTA LTDA', 'sac@comalatacadista.com.br', '(81)99263-6360', '7931798-74', 'COMAL - COMERCIO ATACADISTA');

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

--
-- Extraindo dados da tabela `historico`
--

INSERT INTO `historico` (`codigo`, `horario`, `observacoes`, `produto_codigo`) VALUES
(1, '2020-01-19', 'PRODUTO RECOLHIDO POR ESTA MOLHADO', 7),
(2, '2020-02-08', 'compra do Produto suspenso', 1),
(3, '2020-02-23', 'CONFER?NCIA DA QUANTIDADE', 1);

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
(2, '105.50', 5, 1, 4),
(3, '330.90', 10, 2, 12),
(4, '341.00', 10, 3, 12),
(5, '0.00', 0, 3, 8),
(6, '0.00', 0, 4, 8),
(7, '100.90', 10, 4, 16);

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
(1, '248.90', 10, 3, 7),
(2, '110.20', 10, 3, 11),
(3, '817.29', 81, 4, 16),
(4, '182.40', 10, 5, 8),
(5, '852.50', 25, 6, 12),
(6, '313.20', 20, 7, 13),
(7, '568.40', 20, 7, 10),
(8, '294.00', 20, 7, 3),
(9, '446.00', 20, 7, 2),
(10, '335.60', 20, 7, 9),
(11, '201.80', 20, 8, 16),
(12, '341.00', 10, 9, 12),
(13, '192.40', 10, 9, 8),
(14, '58.96', 2, 10, 19),
(15, '42.20', 2, 10, 4),
(16, '68.20', 2, 10, 12),
(17, '38.48', 2, 11, 8),
(18, '20.18', 2, 11, 16),
(19, '284.20', 10, 11, 10),
(20, '1425.60', 40, 12, 20),
(21, '567.00', 10, 12, 15),
(22, '567.00', 10, 13, 15),
(23, '1244.50', 50, 14, 7),
(24, '156.60', 10, 15, 13),
(25, '284.20', 10, 15, 10),
(26, '4768.00', 40, 16, 14),
(27, '294.80', 10, 17, 19),
(28, '211.00', 10, 17, 4),
(29, '682.00', 20, 17, 12),
(30, '192.40', 10, 17, 8),
(31, '100.90', 10, 17, 16),
(32, '3410.00', 100, 18, 12),
(33, '1924.00', 100, 18, 8),
(34, '1009.00', 100, 18, 16),
(35, '2653.20', 90, 19, 19),
(36, '1899.00', 90, 19, 4),
(37, '1023.00', 30, 20, 12),
(38, '577.20', 30, 20, 8),
(39, '302.70', 30, 20, 16),
(40, '1409.40', 90, 21, 13),
(41, '2557.80', 90, 21, 10),
(42, '1323.00', 90, 21, 3),
(43, '2007.00', 90, 21, 2),
(44, '1678.00', 100, 21, 9),
(46, '5103.00', 90, 23, 15),
(47, '1782.00', 90, 23, 6),
(48, '3207.60', 90, 24, 20),
(49, '1701.00', 90, 24, 1),
(50, '3240.00', 100, 24, 5),
(51, '2110.00', 100, 25, 4),
(52, '9536.00', 80, 26, 14),
(53, '341.00', 10, 27, 12),
(54, '1364.00', 40, 28, 12),
(55, '3576.00', 30, 29, 14),
(56, '1102.00', 100, 30, 11),
(57, '5960.00', 50, 30, 14),
(58, '152.00', 10, 31, 23);

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
(3, '5960.00', 50, 1, 14),
(4, '357.60', 3, 2, 14),
(5, '341.00', 10, 3, 12),
(6, '192.40', 10, 3, 8),
(7, '100.90', 10, 3, 16),
(8, '228.00', 15, 4, 23),
(9, '192.40', 10, 4, 8);

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
(1, '211.00', 10, 1, 4),
(2, '182.40', 10, 2, 8),
(3, '182.40', 10, 3, 8),
(4, '852.50', 25, 4, 12),
(5, '192.40', 10, 5, 8),
(6, '341.00', 10, 6, 12),
(7, '294.80', 10, 7, 19),
(8, '211.00', 10, 7, 4),
(9, '682.00', 20, 7, 12),
(10, '192.40', 10, 7, 8),
(11, '100.90', 10, 7, 16),
(12, '1102.00', 100, 8, 11),
(13, '5960.00', 50, 8, 14);

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
(7, '765.00', 20, 18, 3),
(11, '341.00', 10, 12, 7),
(12, '192.40', 10, 8, 8),
(16, '341.00', 10, 12, 12),
(17, '105.50', 5, 4, 13),
(18, '170.50', 5, 12, 13),
(19, '50.45', 5, 16, 13),
(20, '682.00', 20, 12, 17),
(21, '1192.00', 10, 14, 17),
(22, '565.04', 56, 16, 18),
(23, '1134.00', 20, 15, 18),
(24, '1425.60', 40, 20, 19),
(25, '2384.00', 20, 14, 20),
(26, '526.80', 30, 17, 21),
(27, '596.00', 5, 14, 22),
(28, '504.50', 50, 16, 23),
(29, '589.60', 20, 19, 23),
(30, '192.40', 10, 8, 24),
(31, '316.50', 15, 4, 24),
(32, '324.00', 10, 5, 24),
(33, '189.00', 10, 1, 24),
(34, '384.80', 20, 8, 25),
(35, '568.40', 20, 10, 25),
(36, '446.00', 20, 2, 26),
(37, '335.60', 20, 9, 26),
(38, '294.00', 20, 3, 27),
(39, '156.60', 10, 13, 28),
(40, '382.50', 10, 18, 28),
(41, '596.00', 5, 14, 29),
(42, '147.40', 5, 19, 30),
(43, '105.50', 5, 4, 30),
(44, '170.50', 5, 12, 30),
(45, '96.20', 5, 8, 30),
(46, '50.45', 5, 16, 30),
(47, '206.36', 7, 19, 31),
(48, '147.70', 7, 4, 31),
(49, '238.70', 7, 12, 31),
(50, '134.68', 7, 8, 31),
(51, '70.63', 7, 16, 31),
(52, '156.60', 10, 13, 31),
(53, '284.20', 10, 10, 32),
(54, '382.50', 10, 18, 32),
(55, '294.80', 10, 19, 33),
(56, '211.00', 10, 4, 33),
(57, '192.40', 10, 8, 34),
(58, '156.60', 10, 13, 35),
(59, '284.20', 10, 10, 35),
(60, '341.00', 10, 12, 36),
(61, '341.00', 10, 12, 37),
(62, '192.40', 10, 8, 37),
(63, '100.90', 10, 16, 37),
(64, '589.60', 20, 19, 38),
(65, '422.00', 20, 4, 38),
(66, '384.80', 20, 8, 38),
(67, '201.80', 20, 16, 38),
(68, '682.00', 20, 12, 38),
(69, '156.60', 10, 13, 38),
(70, '284.20', 10, 10, 38),
(71, '147.00', 10, 3, 38),
(72, '223.00', 10, 2, 38),
(73, '167.80', 10, 9, 38),
(74, '2384.00', 20, 14, 39),
(75, '156.60', 10, 13, 39),
(76, '284.20', 10, 10, 39),
(77, '147.00', 10, 3, 39),
(78, '223.00', 10, 2, 39),
(79, '335.60', 20, 9, 39),
(80, '1134.00', 20, 15, 39),
(81, '396.00', 20, 6, 39),
(82, '712.80', 20, 20, 39),
(83, '378.00', 20, 1, 39),
(84, '648.00', 20, 5, 39),
(85, '1023.00', 30, 12, 40),
(86, '577.20', 30, 8, 40),
(87, '302.70', 30, 16, 40),
(88, '1192.00', 10, 14, 41),
(89, '351.20', 20, 17, 42),
(90, '765.00', 20, 18, 42),
(91, '497.80', 20, 7, 42),
(92, '220.40', 20, 11, 42),
(93, '2980.00', 25, 14, 43),
(94, '1147.50', 30, 18, 43),
(95, '5670.00', 100, 15, 43),
(96, '378.00', 20, 1, 44),
(97, '189.00', 10, 1, 45),
(98, '596.00', 5, 14, 46),
(99, '4212.00', 130, 5, 46),
(100, '5960.00', 50, 14, 47),
(102, '441.00', 30, 3, 49),
(103, '378.00', 20, 1, 49),
(104, '1192.00', 10, 14, 50),
(105, '302.70', 30, 16, 51),
(106, '1192.00', 10, 14, 51),
(107, '378.00', 20, 1, 51),
(108, '2384.00', 20, 14, 52),
(109, '152.00', 10, 23, 53),
(110, '189.00', 10, 1, 53),
(111, '228.00', 15, 23, 54),
(112, '192.40', 10, 8, 54),
(113, '189.00', 10, 1, 55);

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
(1, '2019-12-26 23:36:28', '7755.50', 2, 1),
(2, '2020-01-02 21:13:26', '357.60', 5, 1),
(3, '2020-03-05 23:00:17', '634.30', 4, 1),
(4, '2020-10-03 15:39:56', '420.40', 2, 1);

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
(1, '2019-12-29 11:58:53', '211.00', 10, 1),
(2, '2019-12-31 19:25:53', '182.40', 10, 1),
(3, '2020-01-03 00:02:34', '182.40', 6, 1),
(4, '2020-01-23 22:16:22', '852.50', 9, 1),
(5, '2020-03-05 23:22:34', '192.40', 3, 1),
(6, '2020-03-05 23:23:37', '341.00', 3, 1),
(7, '2020-03-06 15:12:31', '1481.10', 3, 1),
(8, '2020-10-03 15:35:26', '7062.00', 8, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento`
--

CREATE TABLE `pagamento` (
  `codigo` bigint(20) NOT NULL,
  `horario` date NOT NULL,
  `precoTotal` decimal(7,2) NOT NULL,
  `compra_codigo` bigint(20) DEFAULT NULL,
  `fornecedor_codigo` bigint(20) DEFAULT NULL,
  `funcionario_codigo` bigint(20) NOT NULL,
  `tipopagc_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `pagamento`
--

INSERT INTO `pagamento` (`codigo`, `horario`, `precoTotal`, `compra_codigo`, `fornecedor_codigo`, `funcionario_codigo`, `tipopagc_codigo`) VALUES
(3, '2020-01-23', '852.50', 6, 9, 1, 1),
(4, '2020-01-25', '1957.20', 7, 5, 1, 1),
(5, '2020-01-30', '201.80', 8, 4, 1, 1),
(6, '2020-01-31', '533.40', 9, 4, 1, 1),
(7, '2020-02-01', '169.36', 10, 4, 1, 1),
(8, '2020-02-02', '342.86', 11, 2, 1, 1),
(9, '2020-02-15', '567.00', 13, 5, 1, 1),
(10, '2020-03-05', '1244.50', 14, 2, 1, 1),
(11, '2020-03-06', '440.80', 15, 10, 1, 1),
(12, '2020-03-06', '1481.10', 17, 5, 1, 1),
(13, '2020-03-06', '4768.00', 16, 9, 1, 1),
(14, '2020-03-07', '1902.90', 20, 1, 1, 1),
(15, '2020-03-07', '4552.20', 19, 4, 1, 1),
(16, '2020-03-07', '8975.20', 21, 3, 1, 1),
(17, '2020-03-07', '8148.60', 24, 4, 1, 1),
(18, '2020-03-07', '6343.00', 18, 2, 1, 1),
(19, '2020-03-07', '6885.00', 23, 9, 1, 1),
(20, '2020-03-12', '2110.00', 25, 3, 1, 1),
(21, '2020-03-12', '9536.00', 26, 3, 1, 1),
(22, '2020-03-12', '341.00', 27, 2, 1, 1),
(23, '2020-03-12', '1364.00', 28, 3, 1, 1),
(24, '2020-03-31', '3576.00', 29, 9, 1, 1),
(25, '2020-10-03', '152.00', 31, 6, 1, 1);

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
(2, 'RECIFE ANTIGO', '(81)99111-1111', '50.500-200', '', '000.000.001-91', 'administrador@gmail.com', 'ADMINISTRADOR', 23, '00.111.111', 'RUA JOSE BONIFACIO', '(81)1111-1111', 1),
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
  `codbarras` varchar(13) NOT NULL,
  `marckup` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`codigo`, `descricao`, `preco`, `quantidade`, `fornecedor_codigo`, `codbarras`, `marckup`) VALUES
(1, 'MACARRAO VITARELLA 500G', '18.90', 30, 1, '7894566782123', 0),
(2, 'FARINHA DE TRIGO ROSA BRANCA 500G', '22.30', 130, 2, '7891980227425', 0),
(3, 'FARINHA DE MANDIOCA ROCA 500G', '14.70', 100, 3, '7892879318347', 0),
(4, 'ACUCAR CRISTAL OLHO DAGUA 500G', '21.10', 230, 4, '7893761409259', 0),
(5, 'MARGARINA DELINE 250G', '32.40', 0, 5, '7894652590162', 0),
(6, 'GUARANA ANTARTICA 2 LT', '19.80', 130, 6, '7895543681075', 0),
(7, 'SABAO EM PO BEM-TE-VI 500G', '24.89', 40, 7, '7896434772988', 0),
(8, 'BISCOITO TRELOSO CHOCOLATE 130G', '19.24', 120, 8, '7897325863891', 0),
(9, 'FEIJAO CARIOCA AZEDINHO 1KG', '16.78', 130, 9, '7898216954704', 0),
(10, 'CHARQUE PONTA DE AGULHA 1KG', '28.42', 130, 10, '7899107045617', 0),
(11, 'SUCO DE LARANJA AMARELINHO 1LT', '11.02', 140, 1, '7891908227421', 0),
(12, 'AZEITE DE OLIVA GALLO 500ML', '34.10', 180, 2, '7892817318347', 0),
(13, 'CARNE MOIDA KG', '15.66', 130, 3, '7898219547071', 0),
(14, 'WHISKY JOHNNIE WALKER BLACK 1 LT ', '119.20', 70, 4, '7896213002657', 0),
(15, 'FLOCAO DE MILHO CUSCUMIL 500G', '56.70', 30, 5, '7899100456182', 0),
(16, 'CAFE PILAR EXTRA FORTE 500G', '10.09', 100, 6, '7894655390165', 0),
(17, 'PRESUNTO DE PEITO DE PERU 1KG', '17.56', 40, 7, '7895546841070', 0),
(18, 'QUEIJO MUSSARELA 1KG', '38.25', 10, 8, '7896437725981', 0),
(19, 'ACHOCOLATADO NESCAU 1LT', '29.48', 130, 9, '7897328638694', 0),
(20, 'LEITE EM PO NINHO 3KG', '35.64', 130, 10, '7893762409258', 0),
(21, 'MACARRAO PILAR 500G', '16.90', 0, 9, '7893451280010', 0),
(22, 'FARINHA DE MANDIOCA ESTRELA 500G', '11.25', 0, 6, '7895423168702', 0),
(23, 'ARROZ INTEGRAL VALE DOURADO', '15.20', 85, 5, '7893358721456', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `recebimento`
--

CREATE TABLE `recebimento` (
  `codigo` bigint(20) NOT NULL,
  `horario` date NOT NULL,
  `precoTotal` decimal(7,2) NOT NULL,
  `cliente_codigo` bigint(20) DEFAULT NULL,
  `funcionario_codigo` bigint(20) NOT NULL,
  `tipopag_codigo` bigint(20) NOT NULL,
  `venda_codigo` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `recebimento`
--

INSERT INTO `recebimento` (`codigo`, `horario`, `precoTotal`, `cliente_codigo`, `funcionario_codigo`, `tipopag_codigo`, `venda_codigo`) VALUES
(9, '2020-01-23', '532.70', 3, 1, 1, 2),
(10, '2020-01-23', '326.45', 2, 1, 1, 13),
(11, '2020-01-23', '435.30', 4, 1, 1, 1),
(12, '2020-01-23', '341.00', 3, 1, 1, 7),
(13, '2020-01-23', '192.40', 4, 1, 1, 8),
(14, '2020-01-23', '1874.00', 2, 1, 1, 17),
(15, '2020-01-23', '1699.04', 4, 1, 1, 18),
(16, '2020-01-23', '1425.60', 4, 1, 1, 19),
(17, '2020-01-23', '2384.00', 3, 1, 1, 20),
(18, '2020-01-23', '526.80', 6, 1, 1, 21),
(19, '2020-01-24', '596.00', 4, 1, 1, 22),
(20, '2020-01-24', '1094.10', 3, 1, 1, 23),
(21, '2020-01-25', '1021.90', 4, 1, 1, 24),
(22, '2020-01-29', '953.20', 6, 1, 1, 25),
(23, '2020-01-30', '781.60', 6, 1, 1, 26),
(24, '2020-01-30', '294.00', 4, 1, 1, 27),
(25, '2020-01-31', '539.10', 6, 1, 1, 28),
(26, '2020-01-31', '596.00', 2, 1, 1, 29),
(27, '2020-02-01', '570.05', 2, 1, 1, 30),
(28, '2020-02-02', '954.67', 4, 1, 1, 31),
(29, '2020-02-03', '666.70', 5, 1, 1, 32),
(30, '2020-02-15', '192.40', 6, 1, 1, 34),
(31, '2020-02-15', '440.80', 4, 1, 1, 35),
(32, '2020-03-06', '634.30', 4, 1, 1, 37),
(33, '2020-03-06', '341.00', 4, 1, 1, 36),
(34, '2020-03-06', '3258.80', 3, 1, 1, 38),
(35, '2020-03-06', '6799.20', 6, 1, 1, 39),
(36, '2020-03-07', '1902.90', 4, 1, 1, 40),
(37, '2020-03-07', '1192.00', 5, 1, 1, 41),
(38, '2020-03-07', '1834.40', 2, 1, 1, 42),
(39, '2020-03-07', '9797.50', 6, 1, 1, 43),
(40, '2020-03-12', '378.00', 2, 1, 1, 44),
(41, '2020-03-12', '189.00', 2, 1, 1, 45),
(42, '2020-03-12', '4808.00', 6, 1, 1, 46),
(43, '2020-03-12', '5960.00', 6, 1, 1, 47),
(44, '2020-03-12', '819.00', 2, 1, 1, 49),
(45, '2020-03-12', '1192.00', 5, 1, 1, 50),
(46, '2020-03-31', '1872.70', 6, 1, 1, 51),
(47, '2020-03-31', '2384.00', 2, 1, 1, 52),
(48, '2020-10-03', '189.00', 4, 1, 1, 55);

-- --------------------------------------------------------

--
-- Estrutura da tabela `sangria`
--

CREATE TABLE `sangria` (
  `codigo` bigint(20) NOT NULL,
  `dataSangria` date NOT NULL,
  `valorSangria` decimal(7,2) NOT NULL,
  `funcionario_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `sangria`
--

INSERT INTO `sangria` (`codigo`, `dataSangria`, `valorSangria`, `funcionario_codigo`) VALUES
(1, '2020-01-31', '200.00', 1),
(2, '2020-02-01', '200.00', 1),
(3, '2020-02-02', '500.00', 1),
(4, '2020-02-03', '300.00', 1),
(5, '2020-03-06', '500.00', 1);

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
  `tipopag_codigo` bigint(20) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `venda`
--

INSERT INTO `venda` (`codigo`, `horario`, `precoTotal`, `vencimento`, `cliente_codigo`, `funcionario_codigo`, `tipopag_codigo`, `status`) VALUES
(1, '2019-12-24', '435.30', '2019-12-26', 4, 1, 1, 1),
(2, '2019-12-24', '532.70', '2019-12-26', 3, 1, 1, 1),
(3, '2019-12-24', '940.60', '2020-01-30', 5, 1, 2, 0),
(7, '2020-01-04', '341.00', '2020-01-06', 3, 1, 1, 1),
(8, '2020-01-04', '192.40', '2020-01-06', 4, 1, 1, 1),
(12, '2020-01-05', '341.00', '2020-01-31', 2, 1, 2, 0),
(13, '2020-01-16', '326.45', '2020-01-16', 2, 1, 1, 1),
(17, '2020-01-23', '1874.00', '2020-01-23', 2, 1, 1, 1),
(18, '2020-01-23', '1699.04', '2020-01-23', 4, 1, 1, 1),
(19, '2020-01-23', '1425.60', '2020-01-23', 4, 1, 1, 1),
(20, '2020-01-23', '2384.00', '2020-01-23', 3, 1, 1, 1),
(21, '2020-01-23', '526.80', '2020-01-23', 6, 1, 1, 1),
(22, '2020-01-24', '596.00', '2020-01-24', 4, 1, 1, 1),
(23, '2020-01-24', '1094.10', '2020-01-24', 3, 1, 1, 1),
(24, '2020-01-25', '1021.90', '2020-01-25', 4, 1, 1, 1),
(25, '2020-01-29', '953.20', '2020-01-29', 6, 1, 1, 1),
(26, '2020-01-29', '781.60', '2020-01-29', 6, 1, 1, 1),
(27, '2020-01-30', '294.00', '2020-01-30', 4, 1, 1, 1),
(28, '2020-01-31', '539.10', '2020-01-31', 6, 1, 1, 1),
(29, '2020-01-31', '596.00', '2020-01-31', 2, 1, 1, 1),
(30, '2020-02-01', '570.05', '2020-02-01', 2, 1, 1, 1),
(31, '2020-02-02', '954.67', '2020-02-02', 4, 1, 1, 1),
(32, '2020-02-03', '666.70', '2020-02-03', 5, 1, 1, 1),
(33, '2020-02-15', '505.80', '2020-02-29', 2, 1, 2, 0),
(34, '2020-02-15', '192.40', '2020-02-17', 6, 1, 1, 1),
(35, '2020-02-15', '440.80', '2020-02-17', 4, 1, 1, 1),
(36, '2020-03-05', '341.00', '2020-03-05', 4, 1, 1, 1),
(37, '2020-03-06', '634.30', '2020-03-06', 4, 1, 1, 1),
(38, '2020-03-06', '3258.80', '2020-03-06', 3, 1, 1, 1),
(39, '2020-03-06', '6799.20', '2020-03-06', 6, 1, 1, 1),
(40, '2020-03-06', '1902.90', '2020-03-06', 4, 1, 1, 1),
(41, '2020-03-07', '1192.00', '2020-03-07', 5, 1, 1, 1),
(42, '2020-03-07', '1834.40', '2020-03-07', 2, 1, 1, 1),
(43, '2020-03-07', '9797.50', '2020-03-07', 6, 1, 1, 1),
(44, '2020-03-12', '378.00', '2020-03-12', 2, 1, 1, 1),
(45, '2020-03-12', '189.00', '2020-03-12', 2, 1, 1, 1),
(46, '2020-03-12', '4808.00', '2020-03-12', 6, 1, 1, 1),
(47, '2020-03-12', '5960.00', '2020-03-12', 6, 1, 1, 1),
(49, '2020-03-12', '819.00', '2020-03-12', 2, 1, 1, 1),
(50, '2020-03-12', '1192.00', '2020-03-12', 5, 1, 1, 1),
(51, '2020-03-31', '1872.70', '2020-03-31', 6, 1, 1, 1),
(52, '2020-03-31', '2384.00', '2020-03-31', 2, 1, 1, 1),
(53, '2020-09-20', '341.00', '2020-09-21', 6, 1, 1, 0),
(54, '2020-10-03', '420.40', '2020-11-03', 2, 1, 2, 0),
(55, '2020-10-03', '189.00', '2020-10-03', 4, 1, 1, 1);

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
  ADD UNIQUE KEY `pessoa_codigo` (`pessoa_codigo`),
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
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `sigla` (`sigla`);

--
-- Índices para tabela `fechamento`
--
ALTER TABLE `fechamento`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `UK_sqf049y8wbjdjgsjbnea4j8hd` (`dataFechamento`),
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
  ADD UNIQUE KEY `pessoa_codigo` (`pessoa_codigo`),
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
-- Índices para tabela `pagamento`
--
ALTER TABLE `pagamento`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_aueprk8gyh8re3ccm4xdldljw` (`compra_codigo`),
  ADD KEY `FK_n96x13urlfuvetr21yda9qkl0` (`fornecedor_codigo`),
  ADD KEY `FK_1xenuo9pod8jbascj5gw6cx7k` (`funcionario_codigo`),
  ADD KEY `FK_pson86njbk727wvxewqtnn6w2` (`tipopagc_codigo`);

--
-- Índices para tabela `pessoa`
--
ALTER TABLE `pessoa`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `cpf` (`cpf`),
  ADD KEY `FK_ru7rwevg7kj864u8vkyo8vbyi` (`cidade_codigo`);

--
-- Índices para tabela `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `codbarras` (`codbarras`),
  ADD KEY `FK_js72o9rt9rkmbkgwyc8u6vixg` (`fornecedor_codigo`);

--
-- Índices para tabela `recebimento`
--
ALTER TABLE `recebimento`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_hqhje9nys4ctjk11hhp82bvw5` (`cliente_codigo`),
  ADD KEY `FK_r0ek3nv63ft0w8kjxnoy88y2r` (`funcionario_codigo`),
  ADD KEY `FK_bqx0kcsudymual6q1tfxm3sjr` (`tipopag_codigo`),
  ADD KEY `FK_amdmjcih2ihu67nvgtpmdjo1j` (`venda_codigo`);

--
-- Índices para tabela `sangria`
--
ALTER TABLE `sangria`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_kvt5458o791c2t6lmi6awmffc` (`funcionario_codigo`);

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
  ADD UNIQUE KEY `pessoa_codigo` (`pessoa_codigo`),
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
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de tabela `avaria`
--
ALTER TABLE `avaria`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de tabela `cpagar`
--
ALTER TABLE `cpagar`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de tabela `creceber`
--
ALTER TABLE `creceber`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `estado`
--
ALTER TABLE `estado`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de tabela `fechamento`
--
ALTER TABLE `fechamento`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `historico`
--
ALTER TABLE `historico`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `itemavaria`
--
ALTER TABLE `itemavaria`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `itemcompra`
--
ALTER TABLE `itemcompra`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT de tabela `itemorca`
--
ALTER TABLE `itemorca`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `itemorcac`
--
ALTER TABLE `itemorcac`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de tabela `itemvenda`
--
ALTER TABLE `itemvenda`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=114;

--
-- AUTO_INCREMENT de tabela `movimentacao`
--
ALTER TABLE `movimentacao`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `orcamento`
--
ALTER TABLE `orcamento`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `orcamentoc`
--
ALTER TABLE `orcamentoc`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `pagamento`
--
ALTER TABLE `pagamento`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de tabela `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de tabela `recebimento`
--
ALTER TABLE `recebimento`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de tabela `sangria`
--
ALTER TABLE `sangria`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `tipoavaria`
--
ALTER TABLE `tipoavaria`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `tipopag`
--
ALTER TABLE `tipopag`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `tipopagc`
--
ALTER TABLE `tipopagc`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `venda`
--
ALTER TABLE `venda`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

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
-- Limitadores para a tabela `pagamento`
--
ALTER TABLE `pagamento`
  ADD CONSTRAINT `FK_1xenuo9pod8jbascj5gw6cx7k` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`),
  ADD CONSTRAINT `FK_aueprk8gyh8re3ccm4xdldljw` FOREIGN KEY (`compra_codigo`) REFERENCES `compra` (`codigo`),
  ADD CONSTRAINT `FK_n96x13urlfuvetr21yda9qkl0` FOREIGN KEY (`fornecedor_codigo`) REFERENCES `fornecedor` (`codigo`),
  ADD CONSTRAINT `FK_pson86njbk727wvxewqtnn6w2` FOREIGN KEY (`tipopagc_codigo`) REFERENCES `tipopagc` (`codigo`);

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
-- Limitadores para a tabela `recebimento`
--
ALTER TABLE `recebimento`
  ADD CONSTRAINT `FK_amdmjcih2ihu67nvgtpmdjo1j` FOREIGN KEY (`venda_codigo`) REFERENCES `venda` (`codigo`),
  ADD CONSTRAINT `FK_bqx0kcsudymual6q1tfxm3sjr` FOREIGN KEY (`tipopag_codigo`) REFERENCES `tipopag` (`codigo`),
  ADD CONSTRAINT `FK_hqhje9nys4ctjk11hhp82bvw5` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`),
  ADD CONSTRAINT `FK_r0ek3nv63ft0w8kjxnoy88y2r` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`);

--
-- Limitadores para a tabela `sangria`
--
ALTER TABLE `sangria`
  ADD CONSTRAINT `FK_kvt5458o791c2t6lmi6awmffc` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`);

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
