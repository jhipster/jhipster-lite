const SPACER_SIZE = 15;

export interface LandscapeConnector {
  lines: LandscapeConnectorLine[];
}

export interface LandscapeConnectorLine {
  start: LandscapeConnectorPosition;
  end: LandscapeConnectorPosition;
}

export interface LandscapeConnectorPosition {
  x: number;
  y: number;
}

export interface BuildConnectorsParameters {
  dependencyElement: HTMLElement;
  dependentElement: DOMRect;
  container: HTMLElement;
}

export const buildConnectors = (parameters: BuildConnectorsParameters): LandscapeConnector => {
  const yPad = parameters.container.scrollTop;
  const xPad = parameters.container.scrollLeft;

  const dependencyElementPosition = parameters.dependencyElement.getBoundingClientRect();

  const dependencyElementX = dependencyElementPosition.x + dependencyElementPosition.width + xPad;
  const dependencyElementY = Math.round(dependencyElementPosition.y + dependencyElementPosition.height / 2) + yPad;

  const dependentElementX = parameters.dependentElement.x + xPad;
  const dependentElementY = Math.round(parameters.dependentElement.y + parameters.dependentElement.height / 2) + yPad;

  const dependencyElementSpacer = {
    start: {
      x: dependencyElementX,
      y: dependencyElementY,
    },
    end: {
      x: dependencyElementX + SPACER_SIZE,
      y: dependencyElementY,
    },
  };

  const dependentElementSpacer = {
    start: {
      x: dependentElementX - SPACER_SIZE,
      y: dependentElementY,
    },
    end: {
      x: dependentElementX,
      y: dependentElementY,
    },
  };

  const diagonalLine = {
    start: {
      x: dependencyElementX + SPACER_SIZE,
      y: dependencyElementY,
    },
    end: {
      x: dependentElementX - SPACER_SIZE,
      y: dependentElementY,
    },
  };

  return {
    lines: [dependencyElementSpacer, dependentElementSpacer, diagonalLine],
  };
};
