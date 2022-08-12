const SPACER_SIZE = 15;

export interface LandscapeConnector {
  lines: LandscapeConnectorLine[];
}

export interface LandscapeConnectorLine {
  start: LandscapeConnectorPosition;
  end: LandscapeConnectorPosition;
  startingElement: string;
  endingElement: string;
}

export interface LandscapeConnectorPosition {
  x: number;
  y: number;
}

export interface BuildConnectorsParameters {
  dependencyElementSlug: string;
  dependentElementSlug: string;
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

  const dependencyElementSpacer: LandscapeConnectorLine = {
    start: {
      x: dependencyElementX,
      y: dependencyElementY,
    },
    end: {
      x: dependencyElementX + SPACER_SIZE,
      y: dependencyElementY,
    },
    startingElement: parameters.dependentElementSlug,
    endingElement: parameters.dependencyElementSlug,
  };

  const dependentElementSpacer: LandscapeConnectorLine = {
    start: {
      x: dependentElementX - SPACER_SIZE,
      y: dependentElementY,
    },
    end: {
      x: dependentElementX,
      y: dependentElementY,
    },
    startingElement: parameters.dependentElementSlug,
    endingElement: parameters.dependencyElementSlug,
  };

  const diagonalLine: LandscapeConnectorLine = {
    start: {
      x: dependencyElementX + SPACER_SIZE,
      y: dependencyElementY,
    },
    end: {
      x: dependentElementX - SPACER_SIZE,
      y: dependentElementY,
    },
    startingElement: parameters.dependentElementSlug,
    endingElement: parameters.dependencyElementSlug,
  };

  return {
    lines: [dependencyElementSpacer, dependentElementSpacer, diagonalLine],
  };
};
