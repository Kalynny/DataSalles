-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 13-Jul-2019 às 07:57
-- Versão do servidor: 10.3.15-MariaDB
-- versão do PHP: 7.3.6

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
-- Estrutura da tabela `avaria`
--

CREATE TABLE `avaria` (
  `codigo` bigint(20) NOT NULL,
  `descricao` varchar(80) NOT NULL,
  `quantidade` smallint(6) NOT NULL,
  `tipo` char(1) NOT NULL,
  `fornecedor_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cidade`
--

CREATE TABLE `cidade` (
  `codigo` bigint(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `estado_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cidade`
--

INSERT INTO `cidade` (`codigo`, `nome`, `estado_codigo`) VALUES
(1, 'Recife', 1),
(2, 'São Paulo', 2),
(3, 'Olinda', 1),
(4, 'Terezinha', 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `codigo` bigint(20) NOT NULL,
  `dataCadastro` date NOT NULL,
  `liberado` bit(1) NOT NULL,
  `pessoa_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`codigo`, `dataCadastro`, `liberado`, `pessoa_codigo`) VALUES
(1, '2019-06-17', b'1', 1),
(2, '2019-06-28', b'1', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `compra`
--

CREATE TABLE `compra` (
  `codigo` bigint(20) NOT NULL,
  `horario` date NOT NULL,
  `precoTotal` decimal(7,2) NOT NULL,
  `vencimento` date NOT NULL,
  `fornecedor_codigo` bigint(20) DEFAULT NULL,
  `funcionario_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `compra`
--

INSERT INTO `compra` (`codigo`, `horario`, `precoTotal`, `vencimento`, `fornecedor_codigo`, `funcionario_codigo`) VALUES
(2, '2019-07-11', '23.90', '2019-08-11', 3, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cpagar`
--

CREATE TABLE `cpagar` (
  `codigo` bigint(20) NOT NULL,
  `atual` date NOT NULL,
  `precoTotal` decimal(6,2) NOT NULL,
  `vencimento` date NOT NULL,
  `fornecedor_codigo` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cpagar`
--

INSERT INTO `cpagar` (`codigo`, `atual`, `precoTotal`, `vencimento`, `fornecedor_codigo`) VALUES
(1, '2019-07-12', '178.00', '2019-08-13', 3),
(2, '2019-07-12', '178.00', '2019-08-13', 3),
(3, '2019-07-12', '155.00', '2019-08-13', 3),
(4, '2019-07-12', '95.00', '2019-08-15', 3),
(5, '2019-07-12', '9560.00', '2019-07-17', 1),
(7, '2019-07-12', '1550.00', '2019-07-20', 3),
(8, '2019-07-12', '75.20', '2019-08-12', 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `estado`
--

CREATE TABLE `estado` (
  `codigo` bigint(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `sigla` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `estado`
--

INSERT INTO `estado` (`codigo`, `nome`, `sigla`) VALUES
(1, 'Pernambuco', 'PE'),
(2, 'São Paulo', 'SP'),
(3, 'Paraiba', 'PB');

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE `fornecedor` (
  `codigo` bigint(20) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `cnpj` varchar(18) NOT NULL,
  `email` varchar(50) NOT NULL,
  `fone` varchar(50) NOT NULL,
  `ie` varchar(50) NOT NULL,
  `nfantasia` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`codigo`, `descricao`, `cnpj`, `email`, `fone`, `ie`, `nfantasia`) VALUES
(1, 'M Dias Branco Industria e Comercio', '07.206.816/0052-65', 'contato@vitarella.com.br', '(81)99987-4655', '3743890', 'Vitarella Ind. e Comercio'),
(3, 'Asa Industria e Comercio ', '01.551.272/0001-42', 'contato@asa.com.br', '(81)93073-5000', '1742701', 'Asa Industria');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `codigo` bigint(20) NOT NULL,
  `carteiraTrabalho` varchar(15) NOT NULL,
  `dataAdmissao` date NOT NULL,
  `pessoa_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`codigo`, `carteiraTrabalho`, `dataAdmissao`, `pessoa_codigo`) VALUES
(1, '78321/00022', '2017-03-01', 1),
(3, '45678/00031', '2019-07-01', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `historico`
--

CREATE TABLE `historico` (
  `codigo` bigint(20) NOT NULL,
  `horario` date NOT NULL,
  `observacoes` varchar(255) DEFAULT NULL,
  `produto_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `historico`
--

INSERT INTO `historico` (`codigo`, `horario`, `observacoes`, `produto_codigo`) VALUES
(1, '2019-07-13', 'Produto dentro do prazo de validade', 2),
(2, '2019-07-13', 'Entrega fora do prazo determinado', 1),
(3, '2019-07-13', 'Fora do dia de entrega', 1),
(5, '2019-07-13', 'Não e data de entrega', 1);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `itemcompra`
--

INSERT INTO `itemcompra` (`codigo`, `precoParcial`, `quantidade`, `compra_codigo`, `produto_codigo`) VALUES
(1, '23.90', 10, 2, 2);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `itemorca`
--

INSERT INTO `itemorca` (`codigo`, `precoParcial`, `quantidade`, `orcamento_codigo`, `produto_codigo`) VALUES
(1, '18.90', 10, 1, 1);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `itemvenda`
--

INSERT INTO `itemvenda` (`codigo`, `precoParcial`, `quantidade`, `produto_codigo`, `venda_codigo`) VALUES
(1, '18.90', 10, 1, 1),
(2, '18.90', 10, 1, 2),
(3, '18.90', 10, 1, 3);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `orcamento`
--

INSERT INTO `orcamento` (`codigo`, `horario`, `precoTotal`, `cliente_codigo`, `funcionario_codigo`) VALUES
(1, '2019-07-02 21:19:13', '18.90', 2, 1);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pessoa`
--

INSERT INTO `pessoa` (`codigo`, `bairro`, `celular`, `cep`, `complemento`, `cpf`, `email`, `nome`, `numero`, `rg`, `rua`, `telefone`, `cidade_codigo`) VALUES
(1, 'Madalena', '(81)98829-5777', '50.610-400', 'Casa D', '453.346.414-91', 'marcoscesarfs@gmail.com', 'Marcos Cesar Ferreira da Silva', 429, '00.295.752-1', 'Rua Campos Sales', '(81)3236-7241', 1),
(2, 'Madalena', '(81)98426-1775', '50.070-020', '', '731.593.524-04', 'kleciamarry@gmail.com', 'Klecia Maria da Conceição Melo', 42, '00.357.842-1', 'Rua Jose Bonifacio', '(81)3236-5477', 1),
(3, 'Varadouro', '(81)98475-2216', '53.460-010', '', '546.823.731-99', 'kalynny.samara@gmail.com', 'Kalynny Samara Melo da Silva', 34, '00.389.564-2', 'Rua Jose Bonifácio', '(81)3274-5689', 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `codigo` bigint(20) NOT NULL,
  `descricao` varchar(80) NOT NULL,
  `preco` decimal(6,2) NOT NULL,
  `quantidade` smallint(6) NOT NULL,
  `fornecedor_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`codigo`, `descricao`, `preco`, `quantidade`, `fornecedor_codigo`) VALUES
(1, 'Macarrão Comum Vitarella 500G', '1.89', 300, 1),
(2, 'Pipoca Grão Verde 800g', '23.90', 110, 3);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`codigo`, `ativo`, `senha`, `tipo`, `pessoa_codigo`) VALUES
(6, b'1', '71c4927169b96910301fab87dc29be9b', 'A', 1),
(7, b'1', '1a2832dc8e98c0ddde501186a2b49fd3', 'G', 2),
(8, b'1', 'e10adc3949ba59abbe56e057f20f883e', 'B', 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE `venda` (
  `codigo` bigint(20) NOT NULL,
  `horario` datetime NOT NULL,
  `precoTotal` decimal(7,2) NOT NULL,
  `cliente_codigo` bigint(20) DEFAULT NULL,
  `funcionario_codigo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `venda`
--

INSERT INTO `venda` (`codigo`, `horario`, `precoTotal`, `cliente_codigo`, `funcionario_codigo`) VALUES
(1, '2019-06-29 20:04:33', '18.90', 2, 1),
(2, '2019-07-02 16:47:54', '18.90', 2, 1),
(3, '2019-07-11 17:32:43', '18.90', 1, 3);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `avaria`
--
ALTER TABLE `avaria`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_kqouiqrgtuvaq71lds025r06c` (`fornecedor_codigo`);

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
  ADD KEY `FK_q521nd8whh19ia70o8retc618` (`funcionario_codigo`);

--
-- Índices para tabela `cpagar`
--
ALTER TABLE `cpagar`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_fh4hjt9e4q64ba8b2srwg8mor` (`fornecedor_codigo`);

--
-- Índices para tabela `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`codigo`);

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
-- Índices para tabela `itemvenda`
--
ALTER TABLE `itemvenda`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_afhuh5uapr0pkljm58r86bnof` (`produto_codigo`),
  ADD KEY `FK_34u5r6crbeyi67pm4ptlha46u` (`venda_codigo`);

--
-- Índices para tabela `orcamento`
--
ALTER TABLE `orcamento`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `FK_slv2u118cmbj0tg5422o0q3m1` (`cliente_codigo`),
  ADD KEY `FK_9y1ujpxvo2ratiegaw1q3njlp` (`funcionario_codigo`);

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
  ADD KEY `FK_5twcbl0srrivgeocts0y19trf` (`funcionario_codigo`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `avaria`
--
ALTER TABLE `avaria`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `cidade`
--
ALTER TABLE `cidade`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `compra`
--
ALTER TABLE `compra`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `cpagar`
--
ALTER TABLE `cpagar`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `estado`
--
ALTER TABLE `estado`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `historico`
--
ALTER TABLE `historico`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `itemcompra`
--
ALTER TABLE `itemcompra`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `itemorca`
--
ALTER TABLE `itemorca`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `itemvenda`
--
ALTER TABLE `itemvenda`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `orcamento`
--
ALTER TABLE `orcamento`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `venda`
--
ALTER TABLE `venda`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `avaria`
--
ALTER TABLE `avaria`
  ADD CONSTRAINT `FK_kqouiqrgtuvaq71lds025r06c` FOREIGN KEY (`fornecedor_codigo`) REFERENCES `fornecedor` (`codigo`);

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
  ADD CONSTRAINT `FK_q521nd8whh19ia70o8retc618` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`);

--
-- Limitadores para a tabela `cpagar`
--
ALTER TABLE `cpagar`
  ADD CONSTRAINT `FK_fh4hjt9e4q64ba8b2srwg8mor` FOREIGN KEY (`fornecedor_codigo`) REFERENCES `fornecedor` (`codigo`);

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
-- Limitadores para a tabela `itemvenda`
--
ALTER TABLE `itemvenda`
  ADD CONSTRAINT `FK_34u5r6crbeyi67pm4ptlha46u` FOREIGN KEY (`venda_codigo`) REFERENCES `venda` (`codigo`),
  ADD CONSTRAINT `FK_afhuh5uapr0pkljm58r86bnof` FOREIGN KEY (`produto_codigo`) REFERENCES `produto` (`codigo`);

--
-- Limitadores para a tabela `orcamento`
--
ALTER TABLE `orcamento`
  ADD CONSTRAINT `FK_9y1ujpxvo2ratiegaw1q3njlp` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`),
  ADD CONSTRAINT `FK_slv2u118cmbj0tg5422o0q3m1` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`);

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
  ADD CONSTRAINT `FK_ass4ciu3lp1ipkofx918j7nm7` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
